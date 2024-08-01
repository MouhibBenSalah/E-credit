import { Component, EventEmitter, Input, Output } from '@angular/core';
import { TypeCredit, TypeUnite } from '../Enum/enums';
import { DemandeCredit } from '../entities/DemandeCredit';

@Component({
  selector: 'app-step-two',
  templateUrl: './step-two.component.html',
  styleUrls: ['./step-two.component.css']
})
export class StepTwoComponent {
  @Input() demandeCredit!: DemandeCredit;
  @Output() previousStep = new EventEmitter<void>();
  @Output() nextStep = new EventEmitter<void>();
  @Output() updateData = new EventEmitter<Partial<DemandeCredit>>();

  typeCreditOptions = Object.values(TypeCredit);
  typeUniteOptions = Object.values(TypeUnite);

  updateTypeCredit(event: any) {
    this.updateData.emit({ typeCredit: event.target.value });
  }

  updateTypeUnite(event: any) {
    this.updateData.emit({ unite: event.target.value });
  }

  updateMontant(event: any) {
    this.updateData.emit({ montant: +event.target.value }); 
  }

  updateDuree(event: any) {
    this.updateData.emit({ duree: +event.target.value }); }

  updateInteret(event: any) {
    const value = parseFloat(event.target.value);
  
  if (!isNaN(value) && value >= 0) {
    this.demandeCredit.interet = value;
  } else {
    this.demandeCredit.interet = 0; // Reset or handle invalid input as needed
  }
  this.updateData.emit({ interet: +event.target.value })
} 
  
  
}
