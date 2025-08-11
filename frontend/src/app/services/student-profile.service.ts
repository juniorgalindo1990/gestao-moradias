import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { StudentProfile } from '../student-profile';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class StudentProfileService {
  private apiUrl = 'http://localhost:8080/profile/student';
  private http = inject(HttpClient);
  private authService = inject(AuthService);

  private getAuthHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    if (!token) {      
      return new HttpHeaders();
    }
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  async getStudentProfile(): Promise<StudentProfile | null> {
    const headers = this.getAuthHeaders();
    if (!headers.has('Authorization')) {
        return null;
    }
    try {
      return await firstValueFrom(this.http.get<StudentProfile>(this.apiUrl, { headers }));
    } catch (error) {
      if (error instanceof HttpErrorResponse && error.status === 404) {
        return null;
      }
      console.error('Erro ao buscar perfil do estudante:', error);
      throw error;
    }
  }

  async createOrUpdateStudentProfile(profile: StudentProfile): Promise<StudentProfile> {
    const headers = this.getAuthHeaders();
    try {
      const existingProfile = await this.getStudentProfile();
      
      if (existingProfile) {        
        return await firstValueFrom(this.http.put<StudentProfile>(this.apiUrl, profile, { headers }));
      } else {        
        return await firstValueFrom(this.http.post<StudentProfile>(this.apiUrl, profile, { headers }));
      }
    } catch (error) {
      console.error('Erro ao criar ou atualizar perfil do estudante:', error);
      throw error;
    }
  }
}