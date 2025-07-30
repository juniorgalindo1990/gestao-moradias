import { Inject} from '@angular/core';
import { CanActivateFn, ActivatedRouteSnapshot, Router} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { AuthService } from '../auth/auth.service';

Inject({
  providedIn: 'root'
})

export const authGuard: CanActivateFn = (route: ActivatedRouteSnapshot) => {
  const authService = Inject(AuthService);
  const router = Inject(Router);

  // Obtém a role requerida da rota
  const requiredRole: string | undefined = route.data?.['role'];

  // Verifica se o usuário está autenticado e possui a role necessária
  if (!authService.isAuthenticated()) {
    return router.createUrlTree(['/login']);
  }
  // Se uma role for requerida, verifica se o usuário possui essa role
  if (requiredRole && !authService.hasRole(requiredRole)) {
    return router.createUrlTree(['/unauthorized'])
  }
  // Se tudo estiver ok, permite o acesso
  return true;
};
