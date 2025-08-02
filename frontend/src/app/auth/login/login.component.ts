import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
// Importe o RouterModule
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../auth.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-login',
  standalone: true,
  // Adicione o RouterModule à lista de imports
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  loginForm: FormGroup = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    senha: ['', [Validators.required]]
  });

  onSubmit() {
    if (this.loginForm.valid) {
      const { email, senha } = this.loginForm.value;
      this.authService.login(email, senha)
        .then(response => {
          console.log('Login bem-sucedido:', response);
          this.router.navigate(['/home']);
        })
        .catch(err => {
          console.error('Erro no login:', err);
          alert('Email ou senha inválidos.');
        });
    } else {
      this.loginForm.markAllAsTouched();
    }
  }
}
