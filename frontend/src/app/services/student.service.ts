import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from '../models/student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private apiUrl = 'http://localhost:8080/students';

  constructor(private http: HttpClient) { }

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl);
  }

  getStudent(id: number): Observable<Student> {
    return this.http.get<Student>(`${this.apiUrl}/${id}`);
  }

  createStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.apiUrl, student);
  }

  updateStudent(id: number, student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.apiUrl}/${id}`, student);
  }

  deleteStudent(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  searchStudents(filter: any): Observable<Student[]> {
    let params = new HttpParams();
    if (filter.wifi) {
      params = params.set('wifi', filter.wifi);
    }
    if (filter.garagem) {
      params = params.set('garagem', filter.garagem);
    }
    if (filter.banheiroPrivativo) {
      params = params.set('banheiroPrivativo', filter.banheiroPrivativo);
    }
    return this.http.get<Student[]>(`${this.apiUrl}/search`, { params });
  }
}
