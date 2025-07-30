import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HousingLocationComponent } from '../housing-location/housing-location.component'; // Presumindo que este componente existe para exibir uma única moradia
import { HousingLocation } from '../housing-location'; // Presumindo que esta interface existe
import { HousingService } from '../services/housing.service'; // Usar o novo caminho para o serviço de moradias
import { Router } from '@angular/router';

@Component({
  selector: 'app-housing-list',
  standalone: true,
  imports: [
    CommonModule,
    HousingLocationComponent
  ],
  templateUrl: './housing-list.component.html',
  styleUrls: ['./housing-list.component.css']
})
export class HousingListComponent implements OnInit {
  housingLocationList: HousingLocation[] = [];
  filteredLocationList: HousingLocation[] = [];
  housingService: HousingService = inject(HousingService);
  private router = inject(Router);

  constructor() { }

  ngOnInit(): void {
    this.loadAllHousingLocations();
  }

  async loadAllHousingLocations() {
    try {
      this.housingLocationList = await this.housingService.getAllHousingLocations();
      this.filteredLocationList = [...this.housingLocationList];
    } catch (error) {
      console.error('Erro ao carregar moradias:', error);
    }
  }

  async filterResults(text: string) {
    if (!text) {
      this.filteredLocationList = [...this.housingLocationList];
      return;
    }

    try {
      this.filteredLocationList = await this.housingService.searchHousingLocations(text);
    } catch (error) {
      console.error('Erro ao filtrar resultados:', error);
      this.filteredLocationList = [];
    }
  }

  redirectToDetails(id: number) {
    // Se não há um componente de detalhes, esta rota pode precisar ser criada.
    // Presumindo que você queira criar um componente de detalhes posteriormente,
    // ou redirecionar para uma página genérica.
    this.router.navigate(['/details', id]); // Rota para detalhes, a ser configurada
  }
}