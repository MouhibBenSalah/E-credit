import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DemandeCreditService } from '../services/demande-credit.service';
import { AuthService } from '../services/auth.service';
import { Compte } from '../entities/compte';

@Component({
  selector: 'app-detail-demande-en-cours',
  templateUrl: './detail-demande-en-cours.component.html',
  styleUrls: ['./detail-demande-en-cours.component.css']
})
export class DetailDemandeEnCoursComponent {
  isDialogOpen = true;
  data: any;
  client: any;
  riskScore: number | null = null;
  riskLevel: string | null = null;
  showRecommendation: boolean = false;
  comptes?:Compte[];

  constructor(
    @Inject(MAT_DIALOG_DATA) public dialogData: any, 
    private demandeCreditService: DemandeCreditService,
    private dialogRef: MatDialogRef<DetailDemandeEnCoursComponent>,
    private authService : AuthService

  ) { 
  }
  ngOnInit(): void {
    this.data = this.dialogData;
    this.fetchClientDetails(this.data.userId);
  }
  fetchClientDetails(userId: number): void {
    this.authService.getUserById(userId).subscribe(user => {
      this.client = user;
      this.calculateScore();
      this.calculateRisk();
      this.comptes=user.comptes
    });}

    calculateScore(): void {
      // Implement your score calculation logic here and assign to this.riskScore
    }
  
    calculateRisk(): void {
      // Implement your risk calculation logic here and assign to this.riskLevel
      // Determine if recommendation should be shown based on score and riskLevel
      if (this.riskScore !== null && this.riskLevel === 'Modéré' && this.riskScore > 72) {
        this.showRecommendation = true;
      }
    }

  closeDialog(): void {
    this.dialogRef.close();
  }

}

