import { Component } from '@angular/core';
import { Chart } from 'chart.js';  // Import Chart.js
import { AuthService } from '../services/auth.service';
import { DemandeCreditService } from '../services/demande-credit.service';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
  nbreClients!: number;
  nbreDemandes! :number;
  constructor(private authService: AuthService , private demandeCreditService : DemandeCreditService) { }

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

  }

 
    orders = [
      {
        id: 1,
        customerName: 'Zinzua Chan Lee',
        customerImage: '../../assets/images/fcbk.png',
        type: 'Amenagement',
        unite: 'trimestrielle',
        duree: '5 ans',
        status: 'Acceptée',
        montant: '128.90 DT'
      },  {
        id: 1,
        customerName: 'Zinzua Chan Lee',
        customerImage: '../../assets/images/fcbk.png',
        type: 'Amenagement',
        unite: 'trimestrielle',
        duree: '5 ans',
        status: 'Rejetée',
        montant: '128.90 DT'
      }, {
        id: 1,
        customerName: 'Zinzua Chan Lee',
        customerImage: '../../assets/images/fcbk.png',
        type: 'Amenagement',
        unite: 'trimestrielle',
        duree: '5 ans',
        status: 'Acceptée',
        montant: '128.90 DT'
      }, {
        id: 1,
        customerName: 'Zinzua Chan Lee',
        customerImage: '../../assets/images/fcbk.png',
        type: 'Amenagement',
        unite: 'trimestrielle',
        duree: '5 ans',
        status: 'Rejetée',
        montant: '128.90 DT'
      },
      {
        id: 1,
        customerName: 'Zinzua Chan Lee',
        customerImage: '../../assets/images/fcbk.png',
        type: 'Amenagement',
        unite: 'trimestrielle',
        duree: '5 ans',
        status: 'Acceptée',
        montant: '128.90 DT'
      },
      {
        id: 1,
        customerName: 'Zinzua Chan Lee',
        customerImage: '../../assets/images/fcbk.png',
        type: 'Amenagement',
        unite: 'trimestrielle',
        duree: '5 ans',
        status: 'Acceptée',
        montant: '128.90 DT'
      },
      {
        id: 1,
        customerName: 'Zinzua Chan Lee',
        customerImage: '../../assets/images/fcbk.png',
        type: 'Amenagement',
        unite: 'trimestrielle',
        duree: '5 ans',
        status: 'Rejetée',
        montant: '128.90 DT'
      },
    
    
      // Add more orders as needed
    ];

    // Traffic Sources Chart
  }

