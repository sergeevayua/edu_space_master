import { Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';
import { Router } from '@angular/router';
// libs
import { Store } from '@ngrx/store';
import { Subject } from 'rxjs';
// store
import * as fromRoot from '@app/modules/store/app.reducers';
import * as layout from '@app/framework/actions/layout';
import * as toolbar from '@app/framework/actions/toolbar';
// animations
import { ToolbarAnimation } from '@app/framework/animations/animations';
// services
// logger
import { Logger } from '@app/services/logger.service';
import { takeUntil } from 'rxjs/operators';
import { FormBuilder } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { currentMode, currentMode$ } from '../../store/toolbar.store';

const log = new Logger('ToolbarComponent');

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss'],
  animations: [ToolbarAnimation]
})
export class ToolbarComponent implements OnInit, OnDestroy, OnChanges {

  @Input() isMobile: boolean;
  mode: string;
  showSideNav: boolean;
  destroy$: Subject<boolean> = new Subject<boolean>();
  userInfo: any;

  constructor(
    private store: Store<fromRoot.State>,
    public router: Router,
    public fb: FormBuilder,
    public authService: AuthService
  ) {
    log.debug(this.router.url);
  }

  ngOnInit(): void {
    this.store.select(fromRoot.getShowSidenav)
      .pipe(takeUntil(this.destroy$))
      .subscribe((data: any) => {
        this.showSideNav = data;
      });
    currentMode$.pipe(takeUntil(this.destroy$))
      .subscribe((mode: string) => {
        this.mode = mode;
      });
    this.mode = this.authService.isAuthenticated() ? 'authorized' : 'login';
  }

  toggle(): void {
    this.showSideNav ? this.store.dispatch(new layout.CloseSidenav()) : this.store.dispatch(new layout.OpenSidenav());
  }

  redirectTo(link: string): void {
    this.router.navigate(['/', link]);
    currentMode.next(link);
  }

  logout() {
    this.store.dispatch(new toolbar.CloseToolbar());
    currentMode.next('login');
    this.authService.logout();
    setTimeout(() => {
      this.router.navigate(['/login']);
    }, 300);
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  ngOnChanges(changes: SimpleChanges): void {
    log.debug('changes', changes);
  }

}
