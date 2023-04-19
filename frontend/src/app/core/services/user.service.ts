import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { LoginRequest } from '../models/login-request';
import { SignupRequest } from '../models/signup-request';
import { LoginResponse } from '../models/login-response';
import { Observable } from 'rxjs/internal/Observable';
import { tap } from 'rxjs';
import { SignupResponse } from '../models/signup-response';
import { LOCAL_STORAGE } from '../constants/local-storage.token';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(
    @Inject(LOCAL_STORAGE) private storage: Storage,
    private authenticationService: AuthenticationService,
    private http: HttpClient,
  ) {}

  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http
      .post<LoginResponse>(
        'http://localhost:8081/joolemarketplace/users/authenticate',
        loginRequest
      )
      .pipe(
        tap((response) => {
          console.log(response);
          this.storage.setItem('username', response.username);
        })
      );
  }

  signup(signupRequest: SignupRequest) {
    console.log(
      'Signup with username: ' +
        signupRequest.username +
        ' and password: ' +
        signupRequest.password
    );
    return this.http
      .post<SignupResponse>(
        'http://localhost:8081/joolemarketplace/users/register',
        signupRequest
      )
      .pipe(
        tap((response) => {
          this.storage.setItem('username', response.username);
        })
      );
  }

  logout(): void {
    this.authenticationService.removeToken();
    this.removeUsername();
    location.reload();
  }

  get username(): string | null {
    return this.storage.getItem('username');
  }

  removeUsername(): void {
    this.storage.removeItem('username');
  }

  isLoggedIn(): boolean {
    return this.username !== null;
  }
}
