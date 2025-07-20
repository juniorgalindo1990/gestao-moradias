import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // Alerreandro confirmar a URL do backend
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }
  
  register(userData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, userData);
  }
  
  login(credentials: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials);
  }
  
  saveToken(token: string): void {
    localStorage.setItem('authToken', token);
  }
  
  getToken(): string | null {
    return localStorage.getItem('authToken');
  }
  
  logout(): void {
    localStorage.removeItem('authToken');
  }
  
  isLoggedIn(): boolean {
    const token = this.getToken();
    return token !== null;
  }
}