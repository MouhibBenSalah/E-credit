import { Component, EventEmitter, Output } from '@angular/core';
import { Sexe, SituationFamiliale } from '../Enum/enums';

@Component({
  selector: 'app-step-one',
  templateUrl: './step-one.component.html',
  styleUrls: ['./step-one.component.css']
})
export class StepOneComponent {
  
  sexeOptions = Object.values(Sexe);
  situationFamilialeOptions = Object.values(SituationFamiliale);
  
  @Output() nextStep = new EventEmitter<void>();

  next() {
    this.nextStep.emit();
  }

}
