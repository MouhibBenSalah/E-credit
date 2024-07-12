import { LieuNaissance, Role, Sexe, SituationFamiliale } from "../Enum/enums";
import { Compte } from "./compte";

export interface User {
    id: number;
    role: Role;
    numCin: number;
    nom: string;
    prenom: string;
    dateNaiss: Date;
    lieuNaiss: LieuNaissance;
    sexe: Sexe;
    sf: SituationFamiliale;
    profilePicture: string;
    email: string;
    password: string;
    comptes: Compte[];
  }