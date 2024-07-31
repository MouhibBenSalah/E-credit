package com.spring.DemandeCredit.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.DemandeCredit.Enum.Devise;
import com.spring.DemandeCredit.Enum.TypeG;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Garantie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nature;

    @Enumerated(EnumType.STRING)
    private TypeG typeGarantie;

    private float valeur;

    @Enumerated(EnumType.STRING)
    private Devise devise;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "garanties")

    @JsonIgnore
    private Set<DemandeCredit> demandeCredits ;
}
