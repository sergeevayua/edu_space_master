import { AfterViewInit, ChangeDetectorRef, Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Logger } from '../../services/logger.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../../services/data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../services/auth.service';
import { Subject, Subscription } from 'rxjs';
import { User } from '../../framework/interfaces/user';
import { finalize } from 'rxjs/operators';

const log = new Logger('RegistrationComponent');

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit, AfterViewInit, OnDestroy {
  @Input() selectedMode = 'registration';
  registrationForm: FormGroup;
  destroy$: Subject<boolean> = new Subject<boolean>();
  registerUser: User;
  hide = true;
  registrationSub: Subscription;

  constructor(
    public fb: FormBuilder,
    private dataService: DataService,
    private route: ActivatedRoute,
    private router: Router,
    private _snackBar: MatSnackBar,
    private authService: AuthService,
    private cdRef: ChangeDetectorRef,
  ) {
    this.registrationForm = this.fb.group({
      login: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9._%+-]{1,64}@(?:[a-zA-Z0-9-]{1,63}\\.){1,125}[a-zA-Z]{2,63}$')]],
      password: ['', Validators.required],
    });
  }

  ngAfterViewInit(): void {
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
    if (this.registrationSub) {
      this.registrationSub.unsubscribe();
    }
  }

  ngOnInit(): void {
  }

  registration(): void {
    this.registerUser = {
      login: this.registrationForm.value.login,
      password: this.registrationForm.value.password,
    };
    this.registrationSub = this.authService.registerUser(this.registerUser)
      .pipe(
        finalize(() => {
          this.cdRef.markForCheck();
        })
      )
      .subscribe(
        data => {
          log.debug('data', data);
          this.authService.logInUser(data);
          const redirect = this.authService.getRedirectUrl() ? this.authService.getRedirectUrl() : '/lk';
          this.router.navigateByUrl(redirect);
          // this._snackBar.open(data.message, 'Success', {
          //   duration: 2000,
          // });
        },
        err => {
          log.debug('err', err);
          // this._snackBar.open(err.message, 'ERROR', {
          //   duration: 2000,
          // });
        });
  }

}
