import { AfterViewInit, ChangeDetectorRef, Component, Input, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { Logger } from '@app/services/logger.service';
import { DataService } from '../../services/data.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../services/auth.service';
import { User } from '../../framework/interfaces/user';
import { take } from 'rxjs/operators';
import { currentMode } from '../../modules/store/toolbar.store';

const log = new Logger('LoginComponent');

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, AfterViewInit, OnDestroy {
  loginForm: FormGroup;
  signUpForm: FormGroup;
  destroy$: Subject<boolean> = new Subject<boolean>();
  registerUser: User;
  @Input() mode = 'login';
  hide = true;

  constructor(
    public fb: FormBuilder,
    private dataService: DataService,
    private route: ActivatedRoute,
    private router: Router,
    private _snackBar: MatSnackBar,
    private authService: AuthService,
    private cdRef: ChangeDetectorRef,
  ) {
    this.loginForm = this.fb.group({
      login: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  ngAfterViewInit(): void {
  }

  ngOnInit() {
    // check for user logged in
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['/lk']);
    }
  }

  login(): void {
    const loginUser: User = {
      login: this.loginForm.controls.login.value,
      password: this.loginForm.controls.password.value,
      remember: true
    };
    log.debug('login user', loginUser);
    // send login request
    this.authService.authenticate(loginUser)
      .pipe(
        take(1)
      )
      .subscribe(
        data => {
          log.debug('data', data);
          this.authService.logInUser(data);
          this.router.navigate(['/lk']);
        },
        err => {
          log.debug('err', err);
          this._snackBar.open(err.error.message, 'Error', {
            duration: 5000,
          });
        },
        () => {
        });
  }
}
