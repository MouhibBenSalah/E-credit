import { Component } from '@angular/core';

@Component({
  selector: 'app-simulateur',
  templateUrl: './simulateur.component.html',
  styleUrls: ['./simulateur.component.css']
})
export class SimulateurComponent {
  loanAmount: number = 4000;
  loanTerm: number = 12;
  monthlyPayment: number = 357;

  calculatePayment() {
    const interestRate = 5; // Fixed interest rate for simplicity
    const monthlyInterestRate = (interestRate / 100) / 12;
    const numberOfPayments = this.loanTerm;
    this.monthlyPayment = (this.loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
    this.monthlyPayment = parseFloat(this.monthlyPayment.toFixed(2));
  }

}
