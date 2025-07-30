import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { CommonModule } from '@angular/common';

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
    password: ['', [Validators.required, Validators.minLength(6)]],
    role: ['USER']
  });

  onSubmit() {
    if (this.registerForm.valid) {
      this.authService.register(this.registerForm.value)
        .then(user => {
          if (user) {
            alert('Usuário registrado com sucesso!');
            this.router.navigate(['/auth/login']);
          } else {
            alert('Erro no registro. Verifique os dados.');
          }
        })
        .catch(err => alert('Erro no registro: ' + err));
    } else {
      this.registerForm.markAllAsTouched();
    }
  }
}