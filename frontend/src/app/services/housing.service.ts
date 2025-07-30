import { Injectable, inject } from '@angular/core';
import { HousingLocation } from '../housing-location'; // Ajuste o caminho para a interface
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { AuthService } from '../auth/auth.service'; // Ajuste o caminho para o serviço de autenticação

@Injectable({
  providedIn: 'root'
})
export class HousingService {
  private apiUrl = 'http://localhost:8080/location'; // URL da API de localização do backend
  private http = inject(HttpClient);
  private authService = inject(AuthService);

  constructor() { }

  private getAuthHeaders(): HttpHeaders {
    const token = this.authService.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  async getAllHousingLocations(): Promise<HousingLocation[]> {
    try {
      const headers = this.getAuthHeaders();
      // O endpoint /location/search sem parâmetros geralmente retorna todas as localizações
      const response = await firstValueFrom(this.http.get<HousingLocation[]>(`${this.apiUrl}/search`, { headers }));
      return response;
    } catch (error) {
      console.error('Erro ao buscar todas as moradias:', error);
      // Retornar um array vazio ou relançar o erro, dependendo da sua política de tratamento
      return [];
    }
  }

  async getHousingLocationById(id: number): Promise<HousingLocation | undefined> {
    try {
      const headers = this.getAuthHeaders();
      const response = await firstValueFrom(this.http.get<HousingLocation>(`${this.apiUrl}/get/${id}`, { headers }));
      return response;
    } catch (error) {
      console.error(`Erro ao buscar moradia por ID ${id}:`, error);
      return undefined;
    }
  }

  async searchHousingLocations(searchText: string): Promise<HousingLocation[]> {
    try {
      const headers = this.getAuthHeaders();
      let resultsByName: HousingLocation[] = [];
      let resultsByCity: HousingLocation[] = [];

      // Tenta buscar por nome
      try {
        const responseByName = await firstValueFrom(this.http.get<HousingLocation[]>(`${this.apiUrl}/search?name=${searchText}`, { headers }));
        resultsByName = responseByName;
      } catch (error) {
        console.warn('Nenhum resultado por nome ou erro na busca por nome:', error);
      }

      // Tenta buscar por cidade
      try {
        const responseByCity = await firstValueFrom(this.http.get<HousingLocation[]>(`${this.apiUrl}/search?city=${searchText}`, { headers }));
        resultsByCity = responseByCity;
      } catch (error) {
        console.warn('Nenhum resultado por cidade ou erro na busca por cidade:', error);
      }

      // Combina resultados e remove duplicatas
      const combinedResults = [...resultsByName, ...resultsByCity];
      const uniqueResults = Array.from(new Set(combinedResults.map(loc => loc.id)))
                               .map(id => combinedResults.find(loc => loc.id === id)!);
      
      return uniqueResults;

    } catch (error) {
      console.error('Erro ao buscar moradias por texto:', error);
      return [];
    }
  }

  submitApplication(firstName: string, lastName: string, email: string) {
    console.log(`Homes application received: firstName: ${firstName}, lastName: ${lastName}, email: ${email}.`);
  }
}