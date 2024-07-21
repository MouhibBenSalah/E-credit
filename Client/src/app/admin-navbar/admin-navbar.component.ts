import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.css']
})
export class AdminNavbarComponent {
  
  constructor(public auth: AuthService, private router: Router) {}


logout():void{
this.auth.logout();
this.router.navigate(["/login"]);
}
}
