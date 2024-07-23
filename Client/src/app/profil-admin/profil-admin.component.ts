import { Component } from '@angular/core';
import { LieuNaissance, Sexe, SituationFamiliale } from '../Enum/enums';
import { User } from '../entities/user';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profil-admin',
  templateUrl: './profil-admin.component.html',
  styleUrls: ['./profil-admin.component.css']
})
export class ProfilAdminComponent {
  lieuNaissanceOptions = Object.values(LieuNaissance);
  sexeOptions = Object.values(Sexe);
  situationFamilialeOptions = Object.values(SituationFamiliale);

  userForm: FormGroup;
  currentUser: User;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.currentUser = this.authService.currentUser();

    this.userForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      formattedDateNaiss: ['',Validators.required],
      dateNaiss:['',Validators.required],
      lieuNaiss: [''],
      sexe: [''],
      email: ['', [Validators.required, Validators.email]]
    });
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
        console.log(data);
        this.userForm.patchValue(data);
      },
      error: (err) => {
        console.error('Error fetching user data', err);
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
    if (this.userForm.valid) {
      this.authService.updateUser(this.currentUser.id, this.userForm.value).subscribe({
        next: (updatedUser) => {
          console.log('User updated successfully:', updatedUser);
          this.getUserData(this.currentUser.id);
          this.router.navigate(['/adminDashboard']);
        },
        error: (err) => {
          console.error('Error updating user data', err);
        }
      });
    }
  }
  resetForm(): void {
    this.userForm.reset({
      nom: null,
      prenom: null,
      dateNaiss: null,
      lieuNaiss: null,
      sexe: null,
      email: null
    });
  }

}
