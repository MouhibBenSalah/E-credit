package com.spring.DemandeCredit.Entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Echeance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idE;
    Date datePaiement;
    Date datePaiementInitiale;
    Float mensualite;
    Float interetsPayes;
    Float capitalRembourse;
    Float capitalRestantDu;;


    @ManyToOne
    ContratCredit contratCredit;
}
