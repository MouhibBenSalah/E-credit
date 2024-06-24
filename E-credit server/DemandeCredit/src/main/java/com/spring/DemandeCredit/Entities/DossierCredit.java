package com.spring.DemandeCredit.Entities;

import com.spring.DemandeCredit.Enum.Dstatus;
import com.spring.DemandeCredit.Enum.TypeCredit;
import com.spring.DemandeCredit.Enum.TypeUnite;
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
public class DossierCredit {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  private Date dateCréation;
  private Date dateMiseAJour;
  @Enumerated(EnumType.STRING)
  private Dstatus statut;

  @OneToMany(mappedBy = "dossierCredit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Set<DemandeCredit> demandesCredit;
}


