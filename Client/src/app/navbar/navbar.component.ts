import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { User } from '../entities/user';

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
      this.currentUser = this.auth.currentUser();    }
    this.fetchCurrentUser();
  }

  fetchCurrentUser(): void {
    const currentUserData = this.auth.currentUser();
    this.auth.getUserById(currentUserData.id).subscribe({
      next: (user: User) => {
        this.currentUser = user;
      },
      error: (err) => {
        console.error('Error fetching user data', err);
      }
    });
  }
  

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }
  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }

}
