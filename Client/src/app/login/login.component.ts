import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  public isLoginFormActive: boolean = true;
 

  toggleForm() {
    this.isLoginFormActive = !this.isLoginFormActive;}
    
  loginForm: FormGroup;
  loginError: string = '';

  signupForm: FormGroup;
  signupError: string = ''; 

    constructor(private router: Router, private fb: FormBuilder, private auth: AuthService) {
      this.loginForm = this.fb.group({
        email: [''],
        password: ['']
      });
  
      this.signupForm = this.fb.group({
        nom: [''],
        prenom: [''],
        numCin: [''],
        email: [''],
        password: [''],
        dateNaiss: [''],
        role: ['Client']
      });
    }

   onSubmit() {
    // Handle form submission
  }
  signupSubmit() {
    const nomControl = this.signupForm.get('nom');
    const prenomControl = this.signupForm.get('prenom');
    const numCinControl = this.signupForm.get('numCin');
    const emailControl = this.signupForm.get('email');
    const passwordControl = this.signupForm.get('password');
    const dateNaissControl = this.signupForm.get('dateNaiss');

    if (emailControl && passwordControl && emailControl.valid && passwordControl.valid && nomControl && prenomControl && nomControl.valid && prenomControl.valid && numCinControl && numCinControl.valid && dateNaissControl && dateNaissControl.valid ) {
      this.signupError = '';

      this.auth.signup(this.signupForm.value).subscribe({
        next: (result) => {
          // this.router.navigate(['/login']);
          this.isLoginFormActive = true;
        },
        error: (err) => {
          this.signupError = "Erreur d'authentification";
          console.log(this.signupError);
        },
      });
      
    } else {
      this.signupError = 'Please enter valid credentials';
      console.log(this.signupError);
    }
    console.log(this.signupForm.value);
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
          this.router.navigate(['/']);
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


