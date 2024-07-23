import { Component } from '@angular/core';
import { Chart } from 'chart.js';  // Import Chart.js

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent {
  salesChart: Chart | undefined;
  trafficChart: Chart | undefined;

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

