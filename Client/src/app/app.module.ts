import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FirstInterfaceComponent } from './first-interface/first-interface.component';
import { FirstComponent } from './first/first.component';
import { EtapesComponent } from './etapes/etapes.component';
import { LoginComponent } from './login/login.component';
import { SimulateurComponent } from './simulateur/simulateur.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ProfileComponent } from './profile/profile.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { DemandeCreditComponent } from './demande-credit/demande-credit.component';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { AuthService } from './services/auth.service';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { FormulaireDemandeCreditComponent } from './formulaire-demande-credit/formulaire-demande-credit.component';
import { StepOneComponent } from './step-one/step-one.component';
import { StepTwoComponent } from './step-two/step-two.component';
import { StepThreeComponent } from './step-three/step-three.component';
import { StepFourComponent } from './step-four/step-four.component';
import { AdminSidebarComponent } from './admin-sidebar/admin-sidebar.component';
import { ProfilAdminComponent } from './profil-admin/profil-admin.component';
import { AdminNavbarComponent } from './admin-navbar/admin-navbar.component';
import { ClientTableComponent } from './client-table/client-table.component';
import { SecurityAdminComponent } from './security-admin/security-admin.component';
import { DemandeDetailsComponent } from './demande-details/demande-details.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import { EcheancesDetailsComponent } from './echeances-details/echeances-details.component';
import { DetailDemandeEnCoursComponent } from './detail-demande-en-cours/detail-demande-en-cours.component';
import { AproposComponent } from './apropos/apropos.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FirstInterfaceComponent,
    FirstComponent,
    EtapesComponent,
    LoginComponent,
    SimulateurComponent,
    ForgetPasswordComponent,
    ResetPasswordComponent,
    ProfileComponent,
    SidebarComponent,
    DemandeCreditComponent,
    AdminDashboardComponent,
    FormulaireDemandeCreditComponent,
    StepOneComponent,
    StepTwoComponent,
    StepThreeComponent,
    StepFourComponent,
    AdminSidebarComponent,
    ProfilAdminComponent,
    AdminNavbarComponent,
    ClientTableComponent,
    SecurityAdminComponent,
    DemandeDetailsComponent,
    EcheancesDetailsComponent,
    DetailDemandeEnCoursComponent,
    AproposComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    
  ],
  providers: [AuthService, {
    provide : HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true,}],

  bootstrap: [AppComponent]
})
export class AppModule { }
