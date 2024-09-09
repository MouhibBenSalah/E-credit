package com.spring.user.Auth;

import com.spring.user.Entity.Compte;
import com.spring.user.Enum.SituationFamiliale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private Long numCin;
    private String nom;
    private String prenom;
    private Date dateNaiss;
   /* private SituationFamiliale sf;
    private Set<CompteRequest> comptes;*/
    private String email;
    private String password;
   // private String profilePicture;

}
