import { Injectable } from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Logger } from '@app/services/logger.service';

const log = new Logger('InterceptorService');

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {
  constructor() {
  }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (req.url.endsWith('.json') || req.url.includes('login') || req.url.includes('registration')) {
      return next.handle(req)
        .pipe(
          tap((event: HttpEvent<any>) => {
            return;
          })
        );
    } else {
      const token = localStorage.getItem('auth_token');
      const tokenReq = req.clone({
        setHeaders: { Authorization: `Bearer ${token}` }
      });
      return next.handle(tokenReq).pipe(
        map((event: HttpResponse<any>) => {
          return event;
        }),
        catchError((error: HttpErrorResponse) => {
          return throwError(error);
        }));
    }
  }

}
