package com.spring.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemandeCreditDTO {
    private Long id;
    private Date dateEntreeRelation;
    private String numDemande;
    private float montant;
    private Float interet;
    private Integer duree;



}
