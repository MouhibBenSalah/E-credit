import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Devise, Sexe, TypeCredit, TypeG, TypeUnite } from '../Enum/enums';
import { Garantie } from '../entities/DTO/GarantieDTO';

@Component({
  selector: 'app-step-three',
  templateUrl: './step-three.component.html',
  styleUrls: ['./step-three.component.css']
})
export class StepThreeComponent {
  @Input() garanties: Garantie[] = [];
  @Output() updateGaranties = new EventEmitter<Garantie[]>();
  @Output() nextStep = new EventEmitter<void>();
  @Output() previousStep = new EventEmitter<void>();

  typeOptions = Object.values(TypeG);
  deviseOptions = Object.values(Devise);

  addGarantie() {
    this.garanties.push({ id: 0, nature: '', typeGarantie: this.typeOptions[0], valeur: 0, devise: this.deviseOptions[0] });
    this.updateGaranties.emit(this.garanties);
  }

  deleteGarantie(index: number) {
    this.garanties.splice(index, 1);
    this.updateGaranties.emit(this.garanties);
  }

  onGarantieChange() {
    this.updateGaranties.emit(this.garanties);
  }

  next() {
    this.nextStep.emit();
  }

  previous() {
    this.previousStep.emit();
  }
}
