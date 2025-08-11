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
      cpf: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
      dataNascimento: ['', [Validators.required]],
      telefone: ['', [Validators.required, Validators.pattern(/^\d{10,11}$/)]],
      periodoAtual: ['', Validators.required],
      universidade: ['', Validators.required],
      curso: ['', Validators.required],
      aceitaAnimais: [false],
      fumante: [false],
      wifi: [false],
      garagem: [false],
      mobiliado: [false],
      banheiroPrivativo: [false]
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
      console.error('Erro ao carregar perfil (pode ser normal se for um novo usuário):', error);
    }
  }

  async onSubmit() {
    if (this.profileForm.valid) {
      try {
        await this.studentProfileService.createOrUpdateStudentProfile(this.profileForm.value);
        alert('Perfil salvo com sucesso!');
        this.router.navigate(['/home']);
      } catch (error: any) {
        console.error('Erro ao salvar perfil:', error);
        alert(`Erro ao salvar perfil: ${error.message}`);
      }
    } else {
      alert('Por favor, preencha todos os campos obrigatórios e corrija os erros.');
    }
  }
}