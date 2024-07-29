import { Component } from '@angular/core';
import { Sexe, SituationFamiliale, Statut, TypeCredit, TypeUnite } from '../Enum/enums';
import { DemandeCredit } from '../entities/DemandeCredit';
import { DemandeCreditService } from '../services/demande-credit.service';
import { AuthService } from '../services/auth.service';
import { User } from '../entities/user';
import { Garantie } from '../entities/DTO/GarantieDTO';

@Component({
  selector: 'app-formulaire-demande-credit',
  templateUrl: './formulaire-demande-credit.component.html',
  styleUrls: ['./formulaire-demande-credit.component.css']
})
export class FormulaireDemandeCreditComponent {

  sexeOptions = Object.values(Sexe);
  situationFamilialeOptions = Object.values(SituationFamiliale);
  step = 1;
  currentUser! :User
  demandeCredit: DemandeCredit = {
    id: 0,
    dateEntreeRelation: new Date(),
    numDemande: '',
    typeCredit: TypeCredit.Amenagement,  
    statut: Statut.EN_COURS,  
    unite: TypeUnite.MENSUELLE,  
    montant: 0,
    interet: 0,
    duree: 0,
    pieceJointes: [],
    garanties: [],
    userId: 0 
  };

  constructor(private demandeCreditService: DemandeCreditService , private authService :AuthService){}


  ngOnInit(): void {
  
      this.currentUser = this.authService.currentUser();    
      this.demandeCredit.userId = this.currentUser.id;
    
  }

  nextStep() {
    this.step++;
  }

  previousStep() {
    this.step--;
  }

  submitForm() {
    console.log('Form submitted!', this.demandeCredit);
    this.demandeCreditService.createDemandeCredit(this.demandeCredit).subscribe(response => {
      console.log('Form submission response:', response);
    });
  }
    updateDemandeCredit(data: Partial<DemandeCredit>) {
    this.demandeCredit = { ...this.demandeCredit, ...data };
  }

  updateGaranties(garanties: Garantie[]) {
    this.demandeCredit.garanties = garanties;
  }
}
