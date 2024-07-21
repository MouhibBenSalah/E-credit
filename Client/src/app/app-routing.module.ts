import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FirstInterfaceComponent } from './first-interface/first-interface.component';
import { LoginComponent } from './login/login.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ProfileComponent } from './profile/profile.component';
import { DemandeCreditComponent } from './demande-credit/demande-credit.component';
import { authGuard, LoggedGuard } from './services/auth.guard';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { adminGuard } from './services/admin.guard';
import { FormulaireDemandeCreditComponent } from './formulaire-demande-credit/formulaire-demande-credit.component';
import { ProfilAdminComponent } from './profil-admin/profil-admin.component';

const routes: Routes = [
  {
    path: '',
    component: FirstInterfaceComponent,
    title: 'Demande Credit GTI',
  },
   {
    path: 'login',
    component: LoginComponent,
    canActivate:[LoggedGuard],
    title: 'Inscrivez Vous',
  },
  {
    path: 'ForgetPass',
    component: ForgetPasswordComponent,
    title: 'entrez mail'
  },
  {
    path: 'reset-password/:token',
    component: ResetPasswordComponent,
    title: 'Reset Your Password' 
  }, 
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate:[authGuard],
    title: 'myProfile'
  }, 
  {
    path :'demandeCredit',
    component: DemandeCreditComponent,
    canActivate:[authGuard],
    title:'Mes Demandes Credit'
  },
  {
    path:'adminDashboard',
    component:AdminDashboardComponent,
    canActivate: [  adminGuard],
    title: 'Admin Dashboard'
  },
  {
    path:'formulaireDemandeCredit',
    component:FormulaireDemandeCreditComponent,
    canActivate:[authGuard],
    title:'Formulaire de Demande Credit'
  },
  {
    path:'profilAdmin',
    component:ProfilAdminComponent,
    canActivate:[adminGuard],
    title:'Profile Admin'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
