import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DemandeCredit } from '../entities/DemandeCredit';

@Injectable({
  providedIn: 'root'
})
export class DemandeCreditService {
  private apiDemande: string = 'http://localhost:4444/DemandesCredit/';

  constructor(private http : HttpClient) {  }


  createDemandeCredit(demandeCredit: DemandeCredit): Observable<DemandeCredit> {
    return this.http.post<DemandeCredit>(this.apiDemande, demandeCredit);
  }

  getDemandeByUserId(id: number): Observable<DemandeCredit[]> {
    return this.http.get<DemandeCredit[]>(`${this.apiDemande}user/${id}`);
  }
  getnbreDemandes() : Observable<number> {
    return this.http.get<number> (`${this.apiDemande}nbreDemandes`)
  }

}
