package com.spring.DemandeCredit.Entities;

import com.spring.DemandeCredit.Enum.Statut;
import com.spring.DemandeCredit.Enum.TypeCredit;
import com.spring.DemandeCredit.Enum.TypeUnite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemandeCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dateEntreeRelation;
    private  String numDemande;

    @Enumerated(EnumType.STRING)
    private TypeCredit typeCredit;

    @Enumerated(EnumType.STRING)
    private TypeUnite unite;
    private float montant;
    private Float interet;
    private Integer duree;
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @OneToMany(mappedBy = "demandeCredit", cascade = CascadeType.ALL)
    private Set<PieceJointe> pieceJointes;

    @ManyToOne
    @JoinColumn(name = "dossier_credit_id")
    private DossierCredit dossierCredit;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Garantie> garanties;

    private Long userId;

}
