import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { StudentProfileService } from '../services/student-profile.service';
import { StudentProfile } from '../student-profile';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-profile',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './student-profile.component.html',
  styleUrls: ['./student-profile.component.css']
})
export class StudentProfileComponent implements OnInit {
  profileForm: FormGroup;
  private fb = inject(FormBuilder);
  private studentProfileService = inject(StudentProfileService);
  private router = inject(Router);

  constructor() {
    this.profileForm = this.fb.group({
      nomeCompleto: ['', Validators.required],
      cpf: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]], // CPF com 11 dígitos
      idade: ['', [Validators.required, Validators.min(18)]], // Idade mínima de 18 anos
      universidade: ['', Validators.required],
      curso: ['', Validators.required],
      aceitaAnimais: [false],
      fumante: [false]
    });
  }

  ngOnInit(): void {
    this.loadProfile();
  }

  async loadProfile() {
    try {
      const profile = await this.studentProfileService.getStudentProfile();
      if (profile) {
        this.profileForm.patchValue(profile);
      }
    } catch (error) {
      console.error('Erro ao carregar perfil:', error);
      alert('Erro ao carregar perfil.');
    }
  }

  async onSubmit() {
    if (this.profileForm.valid) {
      try {
        await this.studentProfileService.createOrUpdateStudentProfile(this.profileForm.value);
        alert('Perfil salvo com sucesso!');
      } catch (error) {
        console.error('Erro ao salvar perfil:', error);
        alert('Erro ao salvar perfil.');
      }
    } else {
      alert('Por favor, preencha todos os campos obrigatórios e corrija os erros.');
    }
  }
}