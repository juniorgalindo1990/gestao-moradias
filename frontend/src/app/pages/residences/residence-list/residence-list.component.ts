import { Component, OnInit, inject } from '@angular/core';
import { ResidenceService } from '../../../services/residence.service';
import { Residence } from '../../../model/residence.model';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-residence-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './residence-list.component.html',
  styleUrls: ['./residence-list.component.css']
})
export class ResidenceListComponent implements OnInit {
  residences: Residence[] = [];
  filterType: string = '';
  filterPurpose: string = '';
  private residenceService = inject(ResidenceService);
  private router = inject(Router);

  ngOnInit() {
    this.loadResidences();
  }

  loadResidences() {
    this.residenceService.getResidences().subscribe(data => {
      this.residences = data;
    });
  }

  applyFilters() {
    if (this.filterType || this.filterPurpose) {
      this.residenceService.filterResidences(this.filterType, this.filterPurpose).subscribe(data => {
        this.residences = data;
      });
    } else {
      this.loadResidences();
    }
  }

  viewDetails(id: number) {
    this.router.navigate(['/residences', id]);
  }

  editResidence(id: number) {
    this.router.navigate(['/residences/edit', id]);
  }

  deleteResidence(id: number) {
    if (confirm('Tem certeza que deseja excluir esta residência?')) {
      this.residenceService.deleteResidence(id).subscribe(() => {
        this.loadResidences();
      });
    }
  }

  createResidence() {
    this.router.navigate(['/residences/new']);
  }
}
