package com.spring.DemandeCredit.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
public class ContratCredit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;
    private String signature;
    private String condition;
    private Date dateC;
    @OneToOne
    private DemandeCredit demandeCredit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="contratCredit")
    private Set<Echeance> echeances;
}
