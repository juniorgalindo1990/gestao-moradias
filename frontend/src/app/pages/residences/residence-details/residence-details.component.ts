import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ResidenceService } from '../../../services/residence.service';
import { Residence } from '../../../model/residence.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-residence-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './residence-details.component.html',
})
export class ResidenceDetailsComponent implements OnInit {
  residence: Residence | null = null;
  private route = inject(ActivatedRoute);
  private residenceService = inject(ResidenceService);

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.residenceService.getResidence(id).subscribe(data => {
        this.residence = data;
      });
    }
  }
}
