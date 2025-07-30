import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { AuthService } from '../auth/auth.service';


@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  // Injeta o serviço de autenticação
  // para acessar o token JWT do usuário autenticado
  constructor(private authService: AuthService) {}


  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();
    // Se o token existir, clona a requisição e adiciona o cabeçalho Authorization
    if (token) {

      const cloned = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      // Retorna a requisição clonada com o cabeçalho Authorization
      return next.handle(cloned);
    }
    // Se não houver token, apenas passa a requisição original
    return next.handle(req);
  }
}
