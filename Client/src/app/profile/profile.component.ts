import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile: any = {}; // Define profile object to hold data
  governorates: string[] = [
    'Tunis', 'Ariana', 'Ben Arous', 'Manouba', 'Nabeul', 'Zaghouan', 'Bizerte', 'Béja', 'Jendouba', 'Kef',
    'Siliana', 'Sousse', 'Monastir', 'Mahdia', 'Sfax', 'Kairouan', 'Kasserine', 'Sidi Bouzid', 'Gabès',
    'Mednine', 'Tataouine', 'Gafsa', 'Tozeur', 'Kebili'
];

  constructor() { }

  ngOnInit(): void {
      // Simulated data (replace with actual data retrieval logic)
      this.profile = {
          cin: '12345678',
          email: 'john.doe@example.com',
          prenom: 'John',
          nom: 'Doe',
          dateNaissance: '1990-01-01',
          lieuNaissance: 'Tunis',
          sexe: 'homme',
          situationFamiliale: 'celibataire',
          numCompte: '1234567890123456',
          devise: 'EUR',
          dateOuverture: '2010-01-01'
      };
  }

}
