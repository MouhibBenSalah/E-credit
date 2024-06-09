package com.spring.DemandeCredit.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private Date dateEntreeRelation;
    private String observation;

    @OneToMany(mappedBy = "demandeCredit", cascade = CascadeType.ALL)
    private Set<PieceJointe> pieceJointes;

    @ManyToOne
    @JoinColumn(name = "dossier_credit_id")
    private DossierCredit dossierCredit;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Garantie> garanties;




}
