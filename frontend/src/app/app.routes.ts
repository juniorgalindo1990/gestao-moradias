import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { SearchStudentsComponent } from './pages/search-students/search-students.component';
import { authGuard } from './auth/auth.guard';
import { StudentProfileComponent } from './student-profile/student-profile.component';
import { HousingListComponent } from './housing-list/housing-list.component'; 

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
    path: 'housing', 
    component: HousingListComponent,
    canActivate: [authGuard],
    data: { role: 'USER' }
  },
  {
    path: 'search-students',
    component: SearchStudentsComponent
  },
  {
    path: '',
    redirectTo: 'auth/login',
    pathMatch: 'full'
  },

];
