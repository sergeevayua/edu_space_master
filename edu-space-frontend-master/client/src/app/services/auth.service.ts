import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../framework/interfaces/user';
import { Logger } from './logger.service';
import { Store } from '@ngrx/store';
import { MediaMatcher } from '@angular/cdk/layout';
import * as fromRoot from '../modules/store/app.reducers';
import { environment } from '@env/environment';
import { currentMode } from '../modules/store/toolbar.store';

const log = new Logger('AuthService');

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private jwtHelper = new JwtHelperService();
  // store the URL so we can redirect after logging in
  private redirectUrl: string;
  private userProfileSubject$ = new BehaviorSubject<any>(null);
  userProfile$ = this.userProfileSubject$.asObservable();
  private log = new Logger('AuthService');

  constructor(
    private http: HttpClient,
    private store: Store<fromRoot.State>,
    private media: MediaMatcher
  ) {
    this.checkValidity();
  }

  // authenticate
  authenticate(data: User): Observable<any> {
    return this.http.post(`${environment.apiUrl}/login`, data);
  }

  // register
  registerUser(data: User): Observable<any> {
    return this.http.post(`${environment.apiUrl}/registration/`, data);
  }

  /**
   * EXTRA METHODS
   */

  // manually log in user
  logInUser(data: any) {
    localStorage.setItem('auth_token', data.tokenId);
    localStorage.setItem('user_id', data.userId);
    localStorage.setItem('user_data', data);
    localStorage.setItem('permissions', data.roleId.map(r => r.permission).join(','));
    setTimeout(() => {
      this.updateUserProfile();
    }, 1000);
    currentMode.next('authorized');
  }

  updateUserProfile() {
    this.getUserProfile(localStorage.getItem('user_id'), localStorage.getItem('auth_token'))
      .subscribe(response => {
        log.debug('resp', response);
        log.debug('this.userProfileSubject$', this.userProfileSubject$.value);
        this.userProfileSubject$.next(response.email);
      });
  }

  getUserProfile(id, token: any): Observable<any> {
    return this.http.get(`${environment.apiUrl}/api/user/${id}`, {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + token
      })
    });
  }

  getPermissions(): string {
    return localStorage.getItem('permissions');
  }

  getUserId() {
    return localStorage.getItem('user_id');
  }

  getRoleId() {
    return localStorage.getItem('role_id');
  }

  logout(): void {
    localStorage.clear();
    this.http.get(`${environment.apiUrl}/logout`);
  }

  /**
   * JWT TOKEN & REDIRECT URL
   */
  getRedirectUrl(): string {
    return this.redirectUrl;
  }

  setRedirectUrl(url: string): void {
    this.redirectUrl = url;
  }

  getToken(): string {
    return localStorage.getItem('auth_token');
  }

  isAuthenticated(): boolean {
    return !this.getToken() ? false : true;
  }

  checkValidity(): void {
    if (this.jwtHelper.isTokenExpired(localStorage.getItem('auth_token'))) {
      log.debug('exp');
      this.logout();
    }
  }
}
