import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FirstInterfaceComponent } from './first-interface/first-interface.component';
import { LoginComponent } from './login/login.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';

const routes: Routes = [
  {
    path: '',
    component: FirstInterfaceComponent,
    title: 'Demande Credit GTI',
  },
   {
    path: 'login',
    component: LoginComponent,
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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
