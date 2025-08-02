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
    email: ['', [Validators.required, Validators.email]],
    senha: ['', [Validators.required, Validators.minLength(6)]],
    role: ['USER']
  });

  async onSubmit() {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    try {
      console.log('Dados que serão enviados para a API:', this.registerForm.value);

      // 1. Espera a conclusão do registro de forma síncrona
      const response = await this.authService.register(this.registerForm.value);

      // 2. Se a linha acima não deu erro, o registro foi um sucesso
      alert('Usuário registrado com sucesso!');
      this.router.navigate(['/auth/login']);

    } catch (error) {
      // 3. Se ocorrer qualquer erro na chamada, ele será capturado aqui
      if (error instanceof HttpErrorResponse && error.status === 409) {
        // Erro específico de "usuário já existe"
        alert(error.error);
      } else {
        // Outros erros (rede, servidor, etc.)
        console.error('Erro detalhado:', error);
        alert('Ocorreu um erro inesperado no registro. Tente novamente.');
      }
    }
  }
}
