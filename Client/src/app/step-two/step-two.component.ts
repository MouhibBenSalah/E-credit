import { Component, EventEmitter, Output } from '@angular/core';
import { Sexe, SituationFamiliale, TypeCredit, TypeUnite } from '../Enum/enums';
import { User } from '../entities/user';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { DemandeCreditService } from '../services/demande-credit.service';
import { DemandeCredit } from '../entities/DemandeCredit';

@Component({
  selector: 'app-step-two',
  templateUrl: './step-two.component.html',
  styleUrls: ['./step-two.component.css']
})
export class StepTwoComponent {
  
  typeCreditOptions = Object.values(TypeCredit);
  typeUniteOptions = Object.values(TypeUnite);

   @Output() stepTwoData = new EventEmitter<any>();

  typeCredit: any;
  unite: any;
  montant: any;
  duree: any;
  interet: any;

  // Call this method when you want to emit the data
  emitData() {
    this.stepTwoData.emit({
      typeCredit: this.typeCredit,
      unite: this.unite,
      montant: this.montant,
      duree: this.duree,
      interet: this.interet
    });
  }
}
