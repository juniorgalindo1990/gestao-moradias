import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  registerForm: FormGroup = this.fb.group({
    name: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    senha: ['', [Validators.required, Validators.minLength(6)]],
    role: ['ESTUDANTE', [Validators.required]]
  });

  async onSubmit() {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    try {
      console.log('Dados que serão enviados para a API:', this.registerForm.value);
      
      const response = await this.authService.register(this.registerForm.value);
      
      
      alert('Usuário registrado com sucesso!');
      this.router.navigate(['/auth/login']);

    } catch (error: any) {      
      console.error('Erro detalhado:', error);      
      alert(error.message || 'Ocorreu um erro inesperado no registro. Tente novamente.');
    }
  }
}