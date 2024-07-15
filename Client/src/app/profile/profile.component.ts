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
  userForm!: FormGroup;
  currentUser!: User;
  error: string = '';

  lieuNaissanceOptions = Object.values(LieuNaissance);
  sexeOptions = Object.values(Sexe);
  situationFamilialeOptions = Object.values(SituationFamiliale);

constructor(private auth: AuthService, private router: Router, private builder: FormBuilder) {}

  ngOnInit() {
    this.currentUser = this.auth.currentUser();
    console.log(this.currentUser);
    this.getUser();

    this.userForm= this.builder.group({
      numCin: this.builder.control(''),
      email: this.builder.control(''),
      prenom: this.builder.control(''),
      nom: this.builder.control(''),
      dateNaiss: this.builder.control(''),
      lieuNaiss: this.builder.control(''),
      sexe: this.builder.control(''),
      sf: this.builder.control(''),
      comptes: this.builder.group({
        numCompte: this.builder.control(''),
        devise: this.builder.control(''),
        dateOuverture: this.builder.control('')
      })
    });

  }

  getUser() {
    this.auth.getUserById(this.currentUser.id).subscribe({
      next: res => {
        console.log(res);
  
        const comptes = res.comptes.map(compte => ({
          numCompte: compte.numCompte,
          devise: compte.devise,
          dateOuverture: compte.dateOuverture
        }));
  
        const formattedDateNaiss = this.formatDate(new Date(res.dateNaiss));

        this.userForm.setValue({
          numCin: res.numCin,
          email: res.email,
          prenom: res.prenom,
          nom: res.nom,
          dateNaiss: formattedDateNaiss,
          lieuNaiss: res.lieuNaiss,
          sexe: res.sexe,
          sf: res.sf,
          comptes: comptes 
        }
      );
        
  
      }, error: (err: HttpErrorResponse) => {
        if (err.status === 401) {
          this.auth.logout();
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
    if (this.userForm.valid) {
      this.auth.updateUser(this.currentUser.id, this.userForm.value).subscribe(
        (response: any) => {
          console.log('User updated successfully', response);
        },
        (error: any) => {
          console.error('Error updating user', error);
        }
      );
    }
  }
}