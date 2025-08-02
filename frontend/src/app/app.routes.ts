import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { SearchStudentsComponent } from './pages/search-students/search-students.component';
import { StudentProfileComponent } from './student-profile/student-profile.component';
import { authGuard } from './auth/auth.guard';
import { HomeComponent } from './home/home.component';

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
    path: 'profile',
    component: StudentProfileComponent,
    canActivate: [authGuard],
    data: { role: 'USER' }
  },

  {
    path: 'auth/register',
    component: RegisterComponent
  },
  {
    path: 'search-students',
    component: SearchStudentsComponent
  },  
  
  {
    path: '**',
    redirectTo: 'auth/login'
  }
];
