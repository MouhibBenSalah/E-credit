export enum Role {
    Client = 'Client',
    Admin = 'Admin',
    CHEF_AGENCE = 'CHEF_AGENCE',
    CHARGE_BANQUE = 'CHARGE_BANQUE'
  }
  
  export enum LieuNaissance {
    Bizerte = 'Bizerte',
    Gabès = 'Gabès',
    Gafsa = 'Gafsa',
    Jendouba = 'Jendouba',
    Kairouan = 'Kairouan',
    Kasserine = 'Kasserine',
    Kébili = 'Kébili',
    Kef = 'Kef',
    Mahdia = 'Mahdia',
    LaManouba = 'La Manouba',
    Médenine = 'Médenine',
    Monastir = 'Monastir',
    Nabeul = 'Nabeul',
    Sfax = 'Sfax',
    SidiBouzid = 'Sidi Bouzid',
    Siliana = 'Siliana',
    Sousse = 'Sousse',
    Tataouine = 'Tataouine',
    Tozeur = 'Tozeur',
    Tunis = 'Tunis',
    Zaghouan = 'Zaghouan'
  }
  
  
  export enum Sexe {
    Homme = 'Homme',
    Femme = 'Femme'
  }
  
  export enum SituationFamiliale {
    Celibataire='Celibataire',
    Maried='Maried'
  }
  export enum TypeCredit {
    Personnel ='Personnel',
    Amenagement ='Amenagement',
    AutoNeuve='AutoNeuve',
    AutoOccasion = 'AutoOccasion'
  }
  export enum TypeUnite {
    MENSUELLE ='MENSUELLE',
    SEMESTRIELLE= 'SEMESTRIELLE',
    TRIMESTRIELLE = 'TRIMESTRIELLE',
    ANNUELLE='ANNUELLE'
  }
  export enum TypeG {
    AssuranceVie ='AssuranceVie',
    Hypothèque='Hypothèque',
    Nantissement='Nantissement',
    CessiondeCréances='CessiondeCréances',
    StockdeMarchandises='StockdeMarchandises'
  }
  export enum Devise{
    
      EUR ='EUR',
      USD='USD',
      DT='DT'
      
 
  }
  export enum Statut{
    ACCEPTÉE ='ACCEPTÉE',
    REFUSÉE = 'REFUSÉE',
    EN_COURS = 'EN_COURS'

}
export enum TypeRisque{
  RISQUE ='RISQUE',
  NON_RISQUE = 'NON_RISQUE'
}
export enum TypeCompte{
  PROFESSIONNEL ='PROFESSIONNEL',
  COURANT = 'COURANT',
  EPARGNE = 'EPARGNE',
  BLOQUE ='BLOQUE'
}
export enum EtatCompte{
  POSITIF ='POSITIF',
  NEGATIF = 'NEGATIF'
}
export enum EmploymentType{
  PUBLIC ='PUBLIC',
  PRIVATE = 'PRIVATE',
  SELF_EMPLOYED = 'SELF_EMPLOYED'

}