import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DemandeCredit } from '../entities/DemandeCredit';
import { Echeance } from '../entities/echeance';

@Injectable({
  providedIn: 'root'
})
export class DemandeCreditService {
  private apiDemande: string = 'http://localhost:4444/DemandesCredit';
  private apiEcheance: string = 'http://localhost:4444/echeances';


  constructor(private http : HttpClient) {  }


  createDemandeCredit(demandeCredit: DemandeCredit): Observable<DemandeCredit> {
    return this.http.post<DemandeCredit>(this.apiDemande, demandeCredit);
  }

  getDemandeByUserId(id: number): Observable<DemandeCredit[]> {
    return this.http.get<DemandeCredit[]>(`${this.apiDemande}/user/${id}`);
  }
  getnbreDemandes() : Observable<number> {
    return this.http.get<number> (`${this.apiDemande}/nbreDemandes`)
  }
  generateEcheances(montant: number, duree: number, typeCredit: string): Observable<Echeance[]> {
    // Prepare URL parameters
    const params = new HttpParams()
      .set('montant', montant.toString())
      .set('duree', duree.toString())
      .set('typeCredit', typeCredit);

    // Make the HTTP POST request
    return this.http.post<Echeance[]>(`${this.apiEcheance}/generateEcheances`, null, { params });
  }

}
