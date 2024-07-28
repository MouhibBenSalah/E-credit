import { Component, OnInit } from '@angular/core';
import { Sexe, SituationFamiliale } from '../Enum/enums';
import { DemandeCreditService } from '../services/demande-credit.service';
import { DemandeCredit } from '../entities/DemandeCredit';
import { AuthService } from '../services/auth.service';
import { User } from '../entities/user';

@Component({
  selector: 'app-formulaire-demande-credit',
  templateUrl: './formulaire-demande-credit.component.html',
  styleUrls: ['./formulaire-demande-credit.component.css']
})
export class FormulaireDemandeCreditComponent implements OnInit{

  sexeOptions = Object.values(Sexe);
  situationFamilialeOptions = Object.values(SituationFamiliale);
  step = 1;
  currentUser!: User;

  demandeCredit: DemandeCredit = {
    id: 0,
    dateEntreeRelation: new Date(),
    numDemande: '',
    typeCredit: '' as any, // Provide a default value or cast to any
    unite: '' as any, // Provide a default value or cast to any
    montant: 0, // Provide a default value
    interet: 0, // Provide a default value
    duree: 0, // Provide a default value
    statut :'' as any ,
    pieceJointes: [],
    garanties: [],
    userId: 0 // Provide a default value
  };

  constructor(
    private demandeCreditService: DemandeCreditService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.currentUser = this.authService.currentUser();
    this.demandeCredit.userId = this.currentUser.id;
  }

  nextStep() {
    this.step++;
  }

  previousStep() {
    this.step--;
  }

  onStepTwoData(data: any) {
    this.demandeCredit.typeCredit = data.typeCredit;
    this.demandeCredit.unite = data.unite;
    this.demandeCredit.montant = data.montant;
    this.demandeCredit.duree = data.duree;
    this.demandeCredit.interet = data.interet;
  }

  onStepThreeData(data: any) {
    this.demandeCredit.garanties = data.garanties;
  }

  submitForm() {
    this.demandeCreditService.createDemandeCredit(this.demandeCredit).subscribe(
      response => {
        console.log('Demande created successfully', response);
      },
      error => {
        console.error('Error creating demande', error);
      }
    );
  }
}
