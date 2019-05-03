import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthenticationService } from '../login/authentication.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            if (err.status === 401) {
                console.log(request.url)
                if (!request.url.endsWith('/api/authenticate')) {
                    // Authentication failed and origin was not the login page!
                    this.authenticationService.logout();
                    location.reload(true);
                }
            }

            const error = err.error.message || err.statusText;
            return throwError(err);
        }))
    }
}