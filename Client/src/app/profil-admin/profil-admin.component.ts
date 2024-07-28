import { Component } from '@angular/core';
import { LieuNaissance, Sexe, SituationFamiliale } from '../Enum/enums';
import { User } from '../entities/user';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';

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
  currentUser!: User;
  selectedFile!: File;
  profilePictureUrl: string = '../../assets/images/fcbk.png'; // Initialize with an empty string


  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
  
    this.userForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      formattedDateNaiss: ['',Validators.required],
      dateNaiss:['',Validators.required],
      lieuNaiss: [''],
      sexe: [''],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  ngOnInit(): void {
    this.currentUser = this.authService.currentUser();
    this.getUserData(this.currentUser.id);
    
  }

  getUserData(id: number): void {
    this.authService.getUserById(id).subscribe({
      next: (data: User) => {
        if (data.dateNaiss) {
          data.formattedDateNaiss = this.formatDate(new Date(data.dateNaiss));
        }
        this.userForm.patchValue(data);
         // Set the profile picture URL after fetching user data
         this.profilePictureUrl = 'http://localhost:4444/User/profile-picture/' + data.profilePicture;
         this.currentUser = data;
      },
      error: (err) => {
        console.error('Error fetching user data', err);
      }
    });
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files[0]) {
      this.selectedFile = input.files[0];
      const reader = new FileReader();
      reader.onload = e => this.profilePictureUrl = reader.result as string;
      reader.readAsDataURL(this.selectedFile);
      // Automatically upload the file after selection
      this.uploadProfilePicture();
    }
  }

  triggerFileInputClick(): void {
    const fileInput = document.getElementById('fileInput') as HTMLInputElement;
    fileInput.click();
  }

  uploadProfilePicture(): void {
    if (this.selectedFile) {
      this.authService.uploadProfilePicture(this.currentUser.id, this.selectedFile).subscribe({
        next: (response: string) => {
          console.log('Profile picture uploaded successfully:', response);
          this.profilePictureUrl = 'http://localhost:4444/User/profile-picture/' + this.selectedFile!.name;
          // Optionally refresh user data to display the new profile picture
          this.getUserData(this.currentUser.id);
        },
        error: (err: HttpErrorResponse) => {
          console.error('Error uploading profile picture', err);
        }
      });
    } else {
      console.error('No file selected');
    }
  }

  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');
    return `${year}-${month}-${day}`;
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      if (this.selectedFile) {
        this.uploadProfilePicture();
      }
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
