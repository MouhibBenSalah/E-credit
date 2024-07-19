import { Component, EventEmitter, Output } from '@angular/core';
import { Sexe, SituationFamiliale } from '../Enum/enums';
import { User } from '../entities/user';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-step-one',
  templateUrl: './step-one.component.html',
  styleUrls: ['./step-one.component.css']
})
export class StepOneComponent {
  user!: User ;
  currentUser: User;
  
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
}
