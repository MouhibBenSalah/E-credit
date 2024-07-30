import { Component } from '@angular/core';
import { Chart } from 'chart.js';  // Import Chart.js
import { AuthService } from '../services/auth.service';
import { DemandeCreditService } from '../services/demande-credit.service';
import { DemandeCredit } from '../entities/DemandeCredit';
import { User } from '../entities/user';
import { Statut } from '../Enum/enums';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
  nbreClients!: number;
  nbreDemandes!: number;
  demandesEnCours: any[] = [];
  demandesAccRej: any[] = [];

  constructor(private authService: AuthService, private demandeCreditService: DemandeCreditService) { }

  ngOnInit(): void {
    this.authService.getnbreClients().subscribe(
      (data) => {
        this.nbreClients = data;
      },
      (error) => {
        console.error('Error fetching client count', error);
      }
    );

    this.demandeCreditService.getnbreDemandes().subscribe(
      (data) => {
        this.nbreDemandes = data;
      },
      (error) => {
        console.error('Error fetching demandes count', error);
      }
    );

    this.loadDemandesEnCours();
    this.loadDemandesAccRej();
  }

  loadDemandesEnCours() {
    this.demandeCreditService.getAllDemandesCredit().subscribe((demandes: DemandeCredit[]) => {
      demandes.forEach((demande: DemandeCredit) => {
        if (demande.statut === Statut.EN_COURS) {
          this.authService.getUserById(demande.userId).subscribe((user: User) => {
            this.demandesEnCours.push({
              ...demande,
              customerImage: user.profilePicture,
              customerName: `${user.nom} ${user.prenom}`
            });
          });
        }
      });
    });
  }

  loadDemandesAccRej() {
    this.demandeCreditService.getAllDemandesCredit().subscribe((demandes: DemandeCredit[]) => {
      demandes.forEach((demande: DemandeCredit) => {
        if (demande.statut === Statut.ACCEPTÉE || demande.statut === Statut.REFUSÉE) {
          this.authService.getUserById(demande.userId).subscribe((user: User) => {
            this.demandesAccRej.push({
              ...demande,
              customerImage: user.profilePicture,
              customerName: `${user.nom} ${user.prenom}`
            });
          });
        }
      });
    });
  }
  }

