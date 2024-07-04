import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiURL: string = 'http://localhost:4020/auth/';

  constructor(private http: HttpClient) {}

  login(credentials: any): Observable<any> {
    return this.http.post<any>(`${this.apiURL}login`, credentials);
  }

  signup(author: any): Observable<any> {
    return this.http.post<any>(`${this.apiURL}register`, author);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string {
    return localStorage.getItem('token') || '';
  }

  setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  refreshToken(): Observable<any> {
    return this.http.post<any>(`${this.apiURL}user/refresh-token`, {});
  }

  logout(): void {
    localStorage.removeItem('token');
  }
  sendResetPasswordEmail(email: string) {
    return this.http.post<any>(`${this.apiURL}forgot-password`, { email });
  }
}
