package com.spring.user.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.spring.user.Enum.EtatCompte;
import com.spring.user.Enum.TypeCompte;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(exclude = "user")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int numCompte;
    private Date dateOuvCompte;
    private String deviseC;
    private float solde;
    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;
    private float montantRouge;
    private Integer nombreDeRetardDePaiement;
    @Enumerated(EnumType.STRING)
    private EtatCompte etatDeCompte;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("comptes")
    private User user;

}
