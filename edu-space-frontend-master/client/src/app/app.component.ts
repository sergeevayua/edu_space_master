import { Observable, Subject } from 'rxjs';
import { MediaMatcher } from '@angular/cdk/layout';

import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  enableProdMode,
  Inject,
  OnDestroy,
  OnInit,
  PLATFORM_ID,
  ViewChild
} from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { ActionsSubject, Store } from '@ngrx/store';
import { isPlatformBrowser, Location } from '@angular/common';
import { MatSidenav } from '@angular/material/sidenav';
import { links } from './nav-links';
import { environment } from '@env/environment';
import { finalize, take } from 'rxjs/operators';
// redux
import * as fromRoot from './modules/store/app.reducers';
import * as layout from './framework/actions/layout';

// animations
import { ToolbarAnimation } from '@app/framework/animations/animations';

// logger
import { Logger } from './services/logger.service';

// services
import { DataService } from '@app/services/data.service';
import { ResizedEvent } from 'angular-resize-event';
import { AuthService } from './services/auth.service';
import { currentMode } from './modules/store/toolbar.store';

const log = new Logger('AppComponent');

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  animations: [ToolbarAnimation],
})
export class AppComponent implements OnInit, OnDestroy {

  @ViewChild(MatSidenav, { static: true }) sidenav: MatSidenav;
  isBrowser: boolean;
  links = links;
  navLinks = [];
  mobileQuery: MediaQueryList;
  _mobileQueryListener: () => void;
  destroy$: Subject<boolean> = new Subject<boolean>();
  permissions: any[];
  // redux
  activeRoute: any;
  showSidenav$: Observable<boolean>;
  showToolbar$: Observable<string>;

  // auth
  loggedIn: boolean;
  public isIframe: boolean;
  userPayload: any;

  constructor(
    @Inject(PLATFORM_ID) public platformId: Object,
    private store: Store<fromRoot.State>,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private location: Location,
    private actionsSubject: ActionsSubject,
    private cdRef: ChangeDetectorRef,
    private media: MediaMatcher,
    private dataService: DataService,
    private authService: AuthService,
  ) {
    // console.log('activatedRoute:', activatedRoute);
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => cdRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
    this.showSidenav$ = this.store.select(fromRoot.getShowSidenav);
    this.showToolbar$ = this.store.select(fromRoot.getShowToolbar);
    this.isIframe = window !== window.parent && !window.opener;
  }

  ngOnInit(): void {
    if (environment.production) {
      enableProdMode();
    }
    this.isBrowser = isPlatformBrowser(this.platformId);
    if (this.authService.isAuthenticated()) {
      currentMode.next('authorized');
      this.authService.updateUserProfile();
      log.debug('1asdasd');
      this.permissions = this.authService.getPermissions().split(',');
      this.getAllProfessions();
    } else {
      currentMode.next('login');
    }
  }

  getAllProfessions() {
    this.dataService.getAllProfessions()
      .pipe(
        take(1)
      )
      .subscribe(response => {
        log.debug('prof', response);
        this.links[0].items = [];
        response.forEach(item => {
          item.permission = 'USER';
          this.links[0].items.push(item);
        });
        this.cdRef.markForCheck();
      })
  }

  ngOnDestroy() {
    this.mobileQuery.removeListener(this._mobileQueryListener);
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  closeSidenav() {
    this.store.dispatch(new layout.CloseSidenav());
  }

  onResized(event: ResizedEvent) {
    if (this.mobileQuery.matches) {
      this.store.dispatch(new layout.CloseSidenav());
    } else {
      if (this.loggedIn && window.location.pathname !== '/auth') {
        this.store.dispatch(new layout.OpenSidenav());
      }
    }
  }

}
