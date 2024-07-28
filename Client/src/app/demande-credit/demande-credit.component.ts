import { Component } from '@angular/core';
import { DemandeCredit } from '../entities/DemandeCredit';
import { User } from '../entities/user';
import { DemandeCreditService } from '../services/demande-credit.service';
import { AuthService } from '../services/auth.service';
import { Statut } from '../Enum/enums';

@Component({
  selector: 'app-demande-credit',
  templateUrl: './demande-credit.component.html',
  styleUrls: ['./demande-credit.component.css']
})
export class DemandeCreditComponent {
  demandes: DemandeCredit[] = [];
  currentUser!: User;
  statutEnum = Statut;

  constructor(
    private demandeCreditService: DemandeCreditService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.currentUser = this.authService.currentUser();
    this.getDemandeByUserId(this.currentUser.id);
  }

  getDemandeByUserId(id: number): void {
    this.demandeCreditService.getDemandeByUserId(id).subscribe({
      next: (res) => {
        this.demandes = res;
        console.log(res);
      },
      error: (error) => {
        console.error('Error fetching demandes', error);
      }
    });
  }
}
