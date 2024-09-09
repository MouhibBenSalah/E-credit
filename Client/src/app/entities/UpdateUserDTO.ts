import { LieuNaissance, Sexe, SituationFamiliale } from "../Enum/enums";

// update-user-dto.model.ts
export interface UpdateUserDTO {
    nom: string;
    prenom: string;
    email: string;
    dateNaiss: Date;
    formattedDateNaiss?: string;
    lieuNaiss: LieuNaissance;
    sexe: Sexe;
    sf: SituationFamiliale;
  }
  