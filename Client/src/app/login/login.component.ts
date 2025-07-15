import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { User } from '../entities/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  loginError: string = '';
  currentUser! :User;

    constructor(private router: Router, private fb: FormBuilder, private auth: AuthService) {
      this.loginForm = this.fb.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(8)]]
      });
    }

  loginSubmit() {
    const emailControl = this.loginForm.get('email');
    const passwordControl = this.loginForm.get('password');

    if (emailControl && passwordControl && emailControl.valid && passwordControl.valid) {
      const email = emailControl.value;
      const password = passwordControl.value;
      this.loginError = '';

      let credentials = {
        email: email,
        password: password
      }

      this.auth.login(credentials).subscribe({
        next: (result) => {  
          this.auth.setToken(result.token);
        const currentUser = this.auth.currentUser();

        if (currentUser && currentUser.role === 'Admin') {
          this.router.navigate(['/clients']);  // Admin manages users
        } else if (currentUser && currentUser.role === 'CHEF_AGENCE') {
          this.router.navigate(['/adminDashboard']);  // Chef d'agence manages credit requests
        } else if (currentUser && currentUser.role === 'CHARGE_BANQUE') {
          this.router.navigate(['/']);  // Bank officer default page
        } else {
          this.router.navigate(['/']);  // Default redirect
        }
        
        },
        error: (err) => {
          this.loginError = "Erreur d'authentification";
        },
      });
    } else {
      this.loginError = 'Please enter valid credentials';
    }
  }
}


