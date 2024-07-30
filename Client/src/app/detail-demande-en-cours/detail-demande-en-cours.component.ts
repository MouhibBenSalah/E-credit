import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { DemandeCreditService } from '../services/demande-credit.service';

@Component({
  selector: 'app-detail-demande-en-cours',
  templateUrl: './detail-demande-en-cours.component.html',
  styleUrls: ['./detail-demande-en-cours.component.css']
})
export class DetailDemandeEnCoursComponent {
  isDialogOpen = true;
  data: any;

  constructor(
    @Inject(MAT_DIALOG_DATA) public dialogData: any, 
    private demandeCreditService: DemandeCreditService,
    private dialogRef: MatDialogRef<DetailDemandeEnCoursComponent>,
    private dialog: MatDialog 

  ) { 
    this.data = dialogData;
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

}
