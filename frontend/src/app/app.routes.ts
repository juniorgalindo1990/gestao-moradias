import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { SearchStudentsComponent } from './pages/search-students/search-students.component'; 

export const routes: Routes = [
  { path: 'auth/login', component: LoginComponent },
  { path: 'auth/register', component: RegisterComponent },
  { path: 'search-students', component: SearchStudentsComponent },
  { path: '', redirectTo: 'auth/login', pathMatch: 'full' }
];
