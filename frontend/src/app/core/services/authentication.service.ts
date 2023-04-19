import { Inject, Injectable } from '@angular/core';
import { LOCAL_STORAGE } from '../constants/local-storage.token';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor(@Inject(LOCAL_STORAGE) private storage: Storage) {}

  getToken(): string | null {
    return this.storage.getItem('token');
  }

  setToken(token: string): void {
    this.storage.setItem('token', token);
  }

  removeToken(): void {
    this.storage.removeItem('token');
  }

  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }
}
