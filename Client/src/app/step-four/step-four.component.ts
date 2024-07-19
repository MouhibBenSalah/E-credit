import { Component } from '@angular/core';

@Component({
  selector: 'app-step-four',
  templateUrl: './step-four.component.html',
  styleUrls: ['./step-four.component.css']
})
export class StepFourComponent {

  documents = [
    { nom: 'Bulletin de paie', obligatoire: 'Oui', statut: false, fichier: null },
    { nom: 'CIN', obligatoire: 'Oui', statut: false, fichier: null }
  ];

  onFileChange(event: any, document: any) {
    const file = event.target.files[0];
    if (file) {
      document.fichier = file;
    }
  }
}
