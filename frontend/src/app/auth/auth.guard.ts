import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // Agora chama o método isAuthenticated() que definimos no serviço
  if (authService.isAuthenticated()) {
    return true;
  } else {
    // Redireciona para a página de login se não estiver autenticado
    router.navigate(['/auth/login']);
    return false;
  }
};
