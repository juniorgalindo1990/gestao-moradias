import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { SearchStudentsComponent } from './pages/search-students/search-students.component';
import { StudentProfileComponent } from './student-profile/student-profile.component';
import { authGuard } from './auth/auth.guard';
import { HomeComponent } from './home/home.component';
import { ResidenceListComponent } from './pages/residences/residence-list/residence-list.component';
import { ResidenceFormComponent } from './pages/residences/residence-form/residence-form.component';
import { ResidenceDetailsComponent } from './pages/residences/residence-details/residence-details.component';
import { StudentListComponent } from './pages/students/student-list/student-list.component';
import { StudentFormComponent } from './pages/students/student-form/student-form.component';
import { StudentDetailsComponent } from './pages/students/student-details/student-details.component';
import { ProprietarioProfileComponent } from './proprietario-profile.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'auth/login',
    pathMatch: 'full'
  },
  {
    path: 'auth/login',
    component: LoginComponent,
    title: 'Login'
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [authGuard],
    title: 'Home'
  },
  {
    path: 'profile/student',
    component: StudentProfileComponent,
    canActivate: [authGuard],
    data: { role: 'ESTUDANTE' }
  },
  {
    path: 'profile/proprietario',
    component: ProprietarioProfileComponent,
    canActivate: [authGuard],
    data: { role: 'PROPRIETARIO' },
    title: 'Perfil do Proprietário'
  },
  {
    path: 'auth/register',
    component: RegisterComponent
  },
  {
    path: 'search-students',
    component: SearchStudentsComponent,
    canActivate: [authGuard],
    data: { role: 'PROPRIETARIO' }
  },
  {
    path: 'residences',
    component: ResidenceListComponent,
    canActivate: [authGuard],
    data: { role: 'PROPRIETARIO' },
    title: 'Minhas Residências'
  },
  {
    path: 'residences/new',
    component: ResidenceFormComponent,
    canActivate: [authGuard],
    data: { role: 'PROPRIETARIO' },
    title: 'Nova Residência'
  },
  {
    path: 'residences/edit/:id',
    component: ResidenceFormComponent,
    canActivate: [authGuard],
    data: { role: 'PROPRIETARIO' },
    title: 'Editar Residência'
  },
  {
    path: 'residences/:id',
    component: ResidenceDetailsComponent,
    canActivate: [authGuard],
    title: 'Detalhes da Residência'
  },
  {
    path: 'students',
    component: StudentListComponent,
    canActivate: [authGuard],
    data: { role: 'PROPRIETARIO' },
    title: 'Estudantes'
  },
  {
    path: 'students/new',
    component: StudentFormComponent,
    canActivate: [authGuard],
    data: { role: 'ESTUDANTE' },
    title: 'Novo Estudante'
  },
  {
    path: 'students/edit/:id',
    component: StudentFormComponent,
    canActivate: [authGuard],
    data: { role: 'ESTUDANTE' },
    title: 'Editar Estudante'
  },
  {
    path: 'students/:id',
    component: StudentDetailsComponent,
    canActivate: [authGuard],
    title: 'Detalhes do Estudante'
  },
  {
    path: '**',
    redirectTo: 'auth/login'
  }
];