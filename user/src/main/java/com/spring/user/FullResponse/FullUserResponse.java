package com.spring.user.FullResponse;

import com.spring.user.DTO.DemandeCreditDTO;
import com.spring.user.Enum.Role;
import com.spring.user.Enum.SituationFamiliale;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullUserResponse {

        @Enumerated(EnumType.STRING)
        private Role role;

        private long numCin;
        private String nom;
        private String prenom;
        private Date dateNaiss;
        private SituationFamiliale sf;
        private int NumCompte;
        private Date dateOuvCompte;
        private String deviseC;
        List<DemandeCreditDTO> demandesCredit;


}


