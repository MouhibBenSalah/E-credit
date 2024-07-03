import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  menuOpen = false;
  currentUser: any = null;

  constructor(public auth: AuthService, private router: Router) {}

  ngOnInit(): void {
    if (this.auth.isLoggedIn()) {
      this.currentUser = { imagePath: 'path/to/default/image.png' }; // Replace with actual user details fetching logic
    }
  }

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }
  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }

}
