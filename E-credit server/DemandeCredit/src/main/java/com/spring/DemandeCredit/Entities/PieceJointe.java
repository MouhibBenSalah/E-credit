package com.spring.DemandeCredit.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class PieceJointe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private boolean obligatoire;
    private String nomFichier;
    private String typeMime;
    private Long taille;

    private byte[] data;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "pieceJointes")

    @JsonIgnore
    private Set<DemandeCredit> demandeCredits ;

}
