import { Component, EventEmitter, Output } from '@angular/core';
import { Sexe, SituationFamiliale, TypeCredit, TypeUnite } from '../Enum/enums';

@Component({
  selector: 'app-step-two',
  templateUrl: './step-two.component.html',
  styleUrls: ['./step-two.component.css']
})
export class StepTwoComponent {

  typeCreditOptions = Object.values(TypeCredit);
  typeUniteOptions = Object.values(TypeUnite);


}
