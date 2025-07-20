import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, RouterModule], 
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  
  registerData = {
    nome: '',
    email: '',
    senha: '',
    confirmarSenha: ''
  };

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit() {
    
    if (this.registerData.senha !== this.registerData.confirmarSenha) {
      alert('As senhas não coincidem!');
      return; 
    }

    
    this.authService.register(this.registerData).subscribe({
      next: (response) => {
        console.log('Registro bem-sucedido!', response);
        alert('Usuário registrado com sucesso! Faça o login.');
        
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.error('Erro no registro!', err);
        alert('Falha no registro. Tente novamente.');
      }
    });
  }
}