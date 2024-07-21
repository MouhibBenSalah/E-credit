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
        location: 'Seoul',
        orderDate: '17 Dec, 2022',
        status: 'Delivered',
        amount: 128.90
      },
      {
        id: 2,
        customerName: 'Jeet Saru',
        customerImage: '../../assets/images/fcbk.png',
        location: 'Paris',
        orderDate: '27 Aug, 2023',
        status: 'Cancelled',
        amount: 5350.50
      },
      {
        id: 3,
        customerName: 'Sonal Gharti',
        customerImage: '../../assets/images/fcbk.png',
        location: 'Tokyo',
        orderDate: '14 Mar, 2023',
        status: 'Shipped',
        amount: 210.40
      },
      {
        id: 3,
        customerName: 'Sonal Gharti',
        customerImage: '../../assets/images/fcbk.png',
        location: 'Tokyo',
        orderDate: '14 Mar, 2023',
        status: 'Shipped',
        amount: 210.40
      },  {
        id: 3,
        customerName: 'Sonal Gharti',
        customerImage: '../../assets/images/fcbk.png',
        location: 'Tokyo',
        orderDate: '14 Mar, 2023',
        status: 'Shipped',
        amount: 210.40
      }, {
        id: 3,
        customerName: 'Sonal Gharti',
        customerImage: '../../assets/images/fcbk.png',
        location: 'Tokyo',
        orderDate: '14 Mar, 2023',
        status: 'Shipped',
        amount: 210.40
      }, {
        id: 3,
        customerName: 'Sonal Gharti',
        customerImage: '../../assets/images/fcbk.png',
        location: 'Tokyo',
        orderDate: '14 Mar, 2023',
        status: 'Shipped',
        amount: 210.40
      }, {
        id: 3,
        customerName: 'Sonal Gharti',
        customerImage: '../../assets/images/fcbk.png',
        location: 'Tokyo',
        orderDate: '14 Mar, 2023',
        status: 'Shipped',
        amount: 210.40
      }, {
        id: 3,
        customerName: 'Sonal Gharti',
        customerImage: '../../assets/images/fcbk.png',
        location: 'Tokyo',
        orderDate: '14 Mar, 2023',
        status: 'Shipped',
        amount: 210.40
      },
      // Add more orders as needed
    ];

    // Traffic Sources Chart
  }

