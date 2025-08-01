import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth';

  async login(email: string, senha: string): Promise<boolean> {
    try {
      const response = await fetch(`${this.apiUrl}/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, senha}),
      });

      if (!response.ok) {
        console.error('Login falhou');
        return false;
      }

      const data = await response.json();
      localStorage.setItem('token', data.token);
      return true;

    } catch (error) {
      console.error('Erro ao fazer login:', error);
      return false;
    }
  }

  async register(newUser: Omit<User, 'id'>): Promise<User | null> {
    try {
      const response = await fetch(`${this.apiUrl}/register`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newUser),
      });

      if (!response.ok) {
        console.error('Registro falhou');
        return null;
      }
      return await response.json();
    } catch (error) {
      console.error('Erro ao registrar usuário:', error);
      return null;
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
