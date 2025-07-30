import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private readonly API = 'http://localhost:8080/students/search';

  constructor(private http: HttpClient) {}

  buscarEstudantes(filtros: any): Observable<any[]> {
    let params = new HttpParams();

    if (filtros.preferenciaMoradia) {
      params = params.set('preferenciaMoradia', filtros.preferenciaMoradia);
    }
    if (filtros.aceitaPets !== undefined) {
      params = params.set('aceitaPets', filtros.aceitaPets);
    }
    if (filtros.precisaMobilidadeReduzida !== undefined) {
      params = params.set('precisaMobilidadeReduzida', filtros.precisaMobilidadeReduzida);
    }

  return this.http.get<any[]>(this.API, { params });
}

}
