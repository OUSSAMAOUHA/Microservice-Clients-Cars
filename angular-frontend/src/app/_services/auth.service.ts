import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConstants } from '../common/app.constants';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(credentials): Observable<any> {
    return this.http.post('http://localhost:8080/api/auth/' + 'signin', {
      email: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'signup', {
      displayName: user.displayName,
      email: user.email,
      password: user.password,
      matchingPassword: user.matchingPassword,
      socialProvider: 'LOCAL',
      using2FA: user.using2FA
    }, httpOptions);
  }

  verify(credentials): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'verify', credentials.code, {
    	  headers: new HttpHeaders({ 'Content-Type': 'text/plain' })
    });
  }

  verifyToken(token): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'token/verify', token, {
    	  headers: new HttpHeaders({ 'Content-Type': 'text/plain' })
    });
  }

  resendToken(token): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'token/resend', token, {
    	  headers: new HttpHeaders({ 'Content-Type': 'text/plain' })
    });
  }
}
