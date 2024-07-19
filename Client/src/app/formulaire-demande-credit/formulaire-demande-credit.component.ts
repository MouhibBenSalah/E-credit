import { Component } from '@angular/core';
import { Sexe, SituationFamiliale } from '../Enum/enums';

@Component({
  selector: 'app-formulaire-demande-credit',
  templateUrl: './formulaire-demande-credit.component.html',
  styleUrls: ['./formulaire-demande-credit.component.css']
})
export class FormulaireDemandeCreditComponent {

  sexeOptions = Object.values(Sexe);
  situationFamilialeOptions = Object.values(SituationFamiliale);
  step = 3;

  nextStep() {
    this.step++;
  }

  previousStep() {
    this.step--;
  }

  submitForm() {
    console.log('Form submitted!');
    // Handle form submission logic here
  }

}
