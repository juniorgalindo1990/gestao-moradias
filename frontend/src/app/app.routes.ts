import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { authGuard } from './auth/auth.guard';
import { StudentProfileComponent } from './student-profile/student-profile.component';
import { HousingListComponent } from './housing-list/housing-list.component'; // Importar o novo componente de listagem

export const routes: Routes = [
    {
        path: 'auth/login',
        component: LoginComponent
    },
    {
        path: 'auth/register',
        component: RegisterComponent
    },
    {
        path: 'profile',
        component: StudentProfileComponent,
        canActivate: [authGuard],
        data: { role: 'USER' }
    },
    {
        path: 'housing', // Rota para a lista de moradias
        component: HousingListComponent,
        canActivate: [authGuard], // Protege a rota, exigindo autenticação
        data: { role: 'USER' } // Ou a role apropriada para ver as moradias
    },
    {
        path: '',
        redirectTo: 'auth/login', // Redireciona a rota base para o login
        pathMatch: 'full'
    },
    // Adicione a rota para detalhes de moradia se for criar o componente de detalhes
    // {
    //     path: 'details/:id',
    //     component: DetailsComponent, // Você precisaria criar este componente
    //     canActivate: [authGuard],
    //     data: { role: 'USER' }
    // },
];