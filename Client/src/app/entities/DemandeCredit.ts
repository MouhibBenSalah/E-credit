import { Statut, TypeCredit, TypeUnite } from "../Enum/enums";
import { Garantie } from "./DTO/GarantieDTO";
import { PieceJointe } from "./DTO/PieceJointeDTO";

export interface DemandeCredit {
    id: number;
    dateEntreeRelation: Date;
    numDemande: string;
    typeCredit: TypeCredit;
    statut :Statut;
    unite: TypeUnite;
    montant: number;
    interet: number;
    duree: number;
    pieceJointes: PieceJointe[];
    garanties: Garantie[];
    userId: number;
  }