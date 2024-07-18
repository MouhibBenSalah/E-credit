import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { User } from '../entities/user';
import { Compte } from '../entities/compte';
import { LieuNaissance, Sexe, SituationFamiliale } from '../Enum/enums';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User | null = null;
  accounts: Compte[] = [];
  currentUser: User;
  lieuNaissanceOptions = Object.values(LieuNaissance);
  sexeOptions = Object.values(Sexe);
  situationFamilialeOptions = Object.values(SituationFamiliale);

  constructor(private authService: AuthService, private router: Router) {
    this.currentUser = this.authService.currentUser();
    console.log(this.currentUser);
  }

  ngOnInit(): void {
    this.getUserData(this.currentUser.id);
  }

  getUserData(id: number): void {
    this.authService.getUserById(id).subscribe({
      next: (data: User) => {
        if (data.dateNaiss) {
          data.formattedDateNaiss = this.formatDate(new Date(data.dateNaiss));
        }
        console.log(data)
        this.user = data;
        this.accounts = data.comptes;
      },
      error: (err) => {
        console.error('Error fetching user data', err);
        if (err.status === 401) {
          this.authService.logout();
          this.router.navigate(['/']);
        }
      }
    });
  }
  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

   onSubmit(): void {
    if (this.user) {
      this.authService.updateUser(this.currentUser.id, this.user).subscribe({
        next: (updatedUser) => {
          console.log('User updated successfully:', updatedUser);
          this.getUserData(this.currentUser.id);
          this.router.navigate(['/']);
        },
        error: (err) => {
          console.error('Error updating user data', err);
        }
      });
    }
  }

}