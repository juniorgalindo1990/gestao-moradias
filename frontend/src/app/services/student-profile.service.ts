import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  async getStudentProfile(): Promise<StudentProfile | null> {
    try {
      const headers = this.getAuthHeaders();
      const response = await firstValueFrom(this.http.get<StudentProfile>(this.apiUrl, { headers }));
      return response;
    } catch (error) {
      console.error('Erro ao buscar perfil do estudante:', error);
      throw error;
    }
  }

  async createOrUpdateStudentProfile(profile: StudentProfile): Promise<StudentProfile> {
    try {
      const headers = this.getAuthHeaders();
      let existingProfile: StudentProfile | null = null;
      try {
        existingProfile = await firstValueFrom(this.http.get<StudentProfile>(this.apiUrl, { headers }));
      } catch (error: any) {
        if (error.status !== 404) {
          console.error('Error checking for existing profile:', error);
          throw error;
        }
      }
      
      if (existingProfile) {
        const response = await firstValueFrom(this.http.put<StudentProfile>(this.apiUrl, profile, { headers }));
        return response;
      } else {
        const response = await firstValueFrom(this.http.post<StudentProfile>(this.apiUrl, profile, { headers }));
        return response;
      }
    } catch (error) {
      console.error('Erro ao criar ou atualizar perfil do estudante:', error);
      throw error;
    }
  }
}