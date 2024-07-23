import { Component } from '@angular/core';

@Component({
  selector: 'app-security-admin',
  templateUrl: './security-admin.component.html',
  styleUrls: ['./security-admin.component.css']
})
export class SecurityAdminComponent {
  passwordFieldType: string = 'password';
  
  togglePasswordVisibility() {
    this.passwordFieldType = this.passwordFieldType === 'password' ? 'text' : 'password';
  }

}


