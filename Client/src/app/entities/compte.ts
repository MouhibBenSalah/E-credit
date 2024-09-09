import { EtatCompte, TypeCompte } from "../Enum/enums";

export interface Compte {
  id: number;
  numCompte: number;
  dateOuvCompte: Date; 
  deviseC: string;
  solde: number;
  typeCompte: TypeCompte; 
  montantRouge: number;
  nombreDeRetardDePaiement: number; 
  etatDeCompte: EtatCompte; 
}
