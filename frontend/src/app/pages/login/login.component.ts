import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service'; 

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginData = {
    email: '',
    senha: ''
  };

  
  constructor(private authService: AuthService, private router: Router) { }

  onSubmit() {
    console.log('Tentando logar com:', this.loginData);
    
    this.authService.login(this.loginData).subscribe({
      
      next: (response) => {
        console.log('Login bem-sucedido!', response);
        
        this.authService.saveToken(response.token);
        alert('Login realizado com sucesso!');
      },
      
      error: (err) => {
        console.error('Erro no login!', err);
        alert('Falha no login. Verifique seu email e senha.');
      }
    });
  }
}