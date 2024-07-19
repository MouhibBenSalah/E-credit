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

  ngOnInit(): void {
    this.initializeCharts();
  }

  private initializeCharts(): void {
    // Sales Overview Chart
    const ctxSales = document.getElementById('sales-chart') as HTMLCanvasElement;
    this.salesChart = new Chart(ctxSales.getContext('2d')!, {
      type: 'line',
      data: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [{
          label: 'Sales',
          data: [30, 40, 35, 50, 60, 70, 90],
          borderColor: '#007bff',
          backgroundColor: 'rgba(0, 123, 255, 0.1)',
          fill: true
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false
      }
    });

    // Traffic Sources Chart
  }
}
