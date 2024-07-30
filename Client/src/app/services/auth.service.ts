import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../entities/user';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ChangePasswordRequest } from '../entities/ChangePasswordRequest';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiURL: string = 'http://localhost:4444/auth/';
  private apiUser: string = 'http://localhost:4444/User/';


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
  currentUser(token: string = this.getToken()): any {
    const helper = new JwtHelperService();
    const decoded= helper.decodeToken(token);
    return decoded;
  }
  
  refreshToken(): Observable<any> {
    return this.http.post<any>(`${this.apiURL}user/refresh-token`, {});
  }

  logout(): void {
    localStorage.removeItem('token');
  }
   sendResetPasswordEmail(email: string): Observable<any> {
    const params = new HttpParams().set('email', email);
    return this.http.post(`${this.apiURL}forgot-password`, {}, { params });
  }
  resetPassword(token: string, password: string): Observable<any> {
    return this.http.post(`${this.apiURL}reset-password`, { token, password });
  }
  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUser}id/${id}`);
  }
  
  updateUser(id: number, user: Partial<User>): Observable<User> {
    return this.http.patch<User>(`${this.apiUser}${id}`, user);
  }
  getnbreClients() : Observable<number> {
    return this.http.get<number> (`${this.apiUser}nbreClients`)
  }
  uploadProfilePicture(userId: number, file: File): Observable<string> {
    const formData: FormData = new FormData();
    formData.append('file', file);
  
    return this.http.post(`${this.apiUser}uploadProfilePicture/${userId}`, formData, { responseType: 'text' });
  }
  getAllClients(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUser}clients`);
  }
  deleteUser(idU: number): Observable<any> {
    return this.http.delete(`${this.apiUser}delete/${idU}`, { responseType: 'text' });
  }
  changePassword(request: ChangePasswordRequest): Observable<any> {
    return this.http.patch<any>(`${this.apiUser}changePassword`, request);
  }

}
