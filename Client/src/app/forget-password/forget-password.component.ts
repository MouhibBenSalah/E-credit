import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';


@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.css']
})
export class ForgetPasswordComponent {
  email!: string;

  constructor(private authService: AuthService) { }

  onSubmit() {
    this.authService.sendResetPasswordEmail(this.email).subscribe(
      response => {
        console.log('Email sent successfully:', response);
      },
      error => {
        console.error('Error sending email:', error);
      }
    );
  }
}
