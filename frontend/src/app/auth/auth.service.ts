import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { jwtDecode } from 'jwt-decode';
import { User } from '../user';

interface TokenResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth';
  private http = inject(HttpClient);

  async login(email: string, senha: string): Promise<boolean> {
    try {
      const response = await firstValueFrom(
        this.http.post<TokenResponse>(`${this.apiUrl}/login`, { email, senha })
      );
      if (response && response.token) {
        localStorage.setItem('token', response.token);
        return true;
      }
      return false;
    } catch (error) {
      console.error('Login falhou:', error);
      return false;
    }
  }

  async register(newUser: Omit<User, 'id'>): Promise<User | null> {
    try {
      return await firstValueFrom(
        this.http.post<User>(`${this.apiUrl}/register`, newUser)
      );
    } catch (error) {
      console.error('Erro ao registrar usuário:', error);
      throw error;
    }
  }

  logout(): void {
    localStorage.removeItem('token');
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  hasRole(requiredRole: string): boolean {
    const token = this.getToken();
    if (!token) {
      return false;
    }

    try {
      const decodedToken: any = jwtDecode(token);
      return decodedToken.role === requiredRole;
    } catch (error) {
      console.error('Erro ao decodificar o token:', error);
      return false;
    }
  }
}