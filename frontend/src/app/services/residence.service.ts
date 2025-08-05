import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Residence } from '../models/residence.model';

@Injectable({
  providedIn: 'root'
})
export class ResidenceService {
  private apiUrl = 'http://localhost:8080/residences';

  constructor(private http: HttpClient) { }

  getResidences(): Observable<Residence[]> {
    return this.http.get<Residence[]>(this.apiUrl);
  }

  getResidence(id: number): Observable<Residence> {
    return this.http.get<Residence>(`${this.apiUrl}/${id}`);
  }

  createResidence(residence: Residence): Observable<Residence> {
    return this.http.post<Residence>(this.apiUrl, residence);
  }

  updateResidence(id: number, residence: Residence): Observable<Residence> {
    return this.http.put<Residence>(`${this.apiUrl}/${id}`, residence);
  }

  deleteResidence(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  filterResidences(type: string, purpose: string): Observable<Residence[]> {
    return this.http.get<Residence[]>(`${this.apiUrl}/filter?tipo=${type}&finalidade=${purpose}`);
  }
}
