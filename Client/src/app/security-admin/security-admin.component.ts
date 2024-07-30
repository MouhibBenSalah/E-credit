import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { ChangePasswordRequest } from '../entities/ChangePasswordRequest';

@Component({
  selector: 'app-security-admin',
  templateUrl: './security-admin.component.html',
  styleUrls: ['./security-admin.component.css']
})
export class SecurityAdminComponent {
  passwordForm: FormGroup;
  passwordFieldType: string = 'password';

  constructor(private fb: FormBuilder, private authService: AuthService) {
    this.passwordForm = this.fb.group({
      currentPassword: ['', [Validators.required]],
      newPassword: ['', [Validators.required, Validators.minLength(8)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(8)]]
    }, { validator: this.passwordMatchValidator });
  }

  passwordMatchValidator(frm: FormGroup) {
    return frm.get('newPassword')?.value === frm.get('confirmPassword')?.value
      ? null : { 'mismatch': true };
  }


  togglePasswordVisibility() {
    this.passwordFieldType = this.passwordFieldType === 'password' ? 'text' : 'password';
  }

  onSubmit() {
    
      const request: ChangePasswordRequest = {
        currentPassword: this.passwordForm.value.currentPassword,
        newPassword: this.passwordForm.value.newPassword,
        confirmPassword: this.passwordForm.value.confirmPassword
      };

      this.authService.changePassword(request).subscribe({
        next: () => {
          alert('Password changed successfully!');
          this.passwordForm.reset();
        },
        error: (err) => {
          console.error('Error changing password', err);
          alert('Failed to change password. Please try again.');
        }
      });
    
  }
}


