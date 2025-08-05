import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ResidenceService } from '../../../services/residence.service';
import { Residence } from '../../../model/residence.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-residence-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './residence-form.component.html',
  styleUrls: ['./residence-form.component.css']
})
export class ResidenceFormComponent implements OnInit {
  residenceForm: FormGroup;
  isEditMode = false;
  residenceId: number | null = null;

  private fb = inject(FormBuilder);
  private residenceService = inject(ResidenceService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  constructor() {
    this.residenceForm = this.fb.group({
      descricao: ['', Validators.required],
      tipo: ['', Validators.required],
      finalidade: ['', Validators.required],
      logradouro: ['', Validators.required],
      bairro: ['', Validators.required],
      cidade: ['', Validators.required],
      estado: ['', Validators.required],
      cep: ['', Validators.required],
      wifi: [false],
      garagem: [false],
      mobiliado: [false],
      banheiroPrivativo: [false],
      valorAluguel: [null, Validators.required],
      fotos: this.fb.array([])
    });
  }

  get fotos() {
    return this.residenceForm.get('fotos') as FormArray;
  }

  addFoto() {
    this.fotos.push(this.fb.control(''));
  }

  removeFoto(index: number) {
    this.fotos.removeAt(index);
  }

  ngOnInit(): void {
    this.residenceId = this.route.snapshot.params['id'];
    if (this.residenceId) {
      this.isEditMode = true;
      this.residenceService.getResidence(this.residenceId).subscribe(data => {
        this.residenceForm.patchValue(data);
        if (data.fotos) {
          data.fotos.forEach(foto => {
            this.fotos.push(this.fb.control(foto));
          });
        }
      });
    }
  }

  onSubmit(): void {
    if (this.residenceForm.invalid) {
      return;
    }

    const residenceData = this.residenceForm.value;
    if (this.isEditMode && this.residenceId) {
      this.residenceService.updateResidence(this.residenceId, residenceData).subscribe(() => {
        this.router.navigate(['/residences']);
      });
    } else {
      this.residenceService.createResidence(residenceData).subscribe(() => {
        this.router.navigate(['/residences']);
      });
    }
  }
}
