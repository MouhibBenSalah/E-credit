import { Component } from '@angular/core';
import { Chart } from 'chart.js';  // Import Chart.js
import { AuthService } from '../services/auth.service';
import { DemandeCreditService } from '../services/demande-credit.service';
import { DemandeCredit } from '../entities/DemandeCredit';
import { User } from '../entities/user';
import { Statut } from '../Enum/enums';
import { MatDialog } from '@angular/material/dialog';
import { DetailDemandeEnCoursComponent } from '../detail-demande-en-cours/detail-demande-en-cours.component';

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

  constructor(private authService: AuthService, private demandeCreditService: DemandeCreditService, private dialogRef : MatDialog) { }

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
  accepterD(demande: any) {
    this.updateStatus(demande.id, Statut.ACCEPTÉE);
  }

  refuserD(demande: any) {
    this.updateStatus(demande.id, Statut.REFUSÉE);
  }

  private updateStatus(id: number, status: Statut) {
    this.demandeCreditService.updateStatus(id, status).subscribe(
      response => {
        console.log('Demande mise à jour avec succès', response);
        this.demandesEnCours = this.demandesEnCours.filter(d => d.id !== id);
        if (status === Statut.ACCEPTÉE || status === Statut.REFUSÉE) {
          this.demandesAccRej.push(response);}
      },
      error => {
        console.error('Erreur lors de la mise à jour de la demande', error);
      }
    );
  }

  openDialog(demande: DemandeCredit): void {
    this.dialogRef.open(DetailDemandeEnCoursComponent, {
      data: demande,
     
    });
  }
}
