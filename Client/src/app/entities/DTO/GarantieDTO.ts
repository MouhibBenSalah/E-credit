import { Devise, TypeG } from "src/app/Enum/enums";

export interface Garantie {
    id: number;
    nature: string;
    typeGarantie: TypeG;
    valeur: number;
    devise: Devise;
  }