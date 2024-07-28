import { Component, EventEmitter, Output } from '@angular/core';
import { Sexe, TypeCredit, TypeG, TypeUnite } from '../Enum/enums';

@Component({
  selector: 'app-step-three',
  templateUrl: './step-three.component.html',
  styleUrls: ['./step-three.component.css']
})
export class StepThreeComponent {
  typeOptions = Object.values(TypeG);
  
  garanties = [
    { nature: '', type: '', valeur: '', devise: '' }
  ];

  addGarantie() {
    this.garanties.push({ nature: '', type: '', valeur: '', devise: '' });
  }

  deleteGarantie(index: number) {
    this.garanties.splice(index, 1);
  }
  
  @Output() nextStep = new EventEmitter<void>();

  next() {
    this.nextStep.emit();
  }

}
