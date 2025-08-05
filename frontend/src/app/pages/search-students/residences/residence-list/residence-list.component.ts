import { Component, OnInit, inject } from '@angular/core';
import { ResidenceService } from '../../../services/residence.service';
import { Residence } from '../../../models/residence.model';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-residence-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './residence-list.component.html',
  styleUrls: ['./residence-list.component.css']
})
export class ResidenceListComponent implements OnInit {
  residences: Residence[] = [];
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
