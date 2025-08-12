import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from './auth/auth.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-proprietario-profile',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="profile-container">
      <h2>Perfil do Proprietário</h2>
      <div *ngIf="userInfo">
        <p><strong>Nome:</strong> {{ userInfo.name }}</p>
        <p><strong>Email:</strong> {{ userInfo.sub }}</p>
      </div>
    </div>
  `,
  styles: [`
    .profile-container {
      max-width: 600px;
      margin: 50px auto;
      padding: 30px;
      background-color: #f9f9f9;
      border-radius: 8px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    }
  `]
})
export class ProprietarioProfileComponent implements OnInit {
  authService = inject(AuthService);
  userInfo: { name: string, sub: string } | null = null;

  ngOnInit(): void {
    const token = this.authService.getToken();
    if (token) {
      this.userInfo = jwtDecode(token);
    }
  }
}