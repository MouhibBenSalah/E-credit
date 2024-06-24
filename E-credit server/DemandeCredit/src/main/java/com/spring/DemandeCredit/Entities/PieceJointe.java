package com.spring.DemandeCredit.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PieceJointe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private boolean obligatoire;
    private boolean statut;
    private long taille;
    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "demande_credit_id")
    private DemandeCredit demandeCredit;

}
