package com.spring.DemandeCredit.Entities;

import com.spring.DemandeCredit.Enum.TypeCredit;
import com.spring.DemandeCredit.Enum.TypeUnite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DossierCredit {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  @Enumerated(EnumType.STRING)
  private TypeCredit typeCredit;

  private float montant;
  private int nbrEcheances ;

  @Enumerated(EnumType.STRING)
  private TypeUnite unite;

  @OneToMany(mappedBy = "dossierCredit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<DemandeCredit> demandesCredit;
}


