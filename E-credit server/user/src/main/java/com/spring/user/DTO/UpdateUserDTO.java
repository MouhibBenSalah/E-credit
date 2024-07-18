package com.spring.user.DTO;

import com.spring.user.Enum.LIEU_NAISSANCE;
import com.spring.user.Enum.SEXE;
import com.spring.user.Enum.SituationFamiliale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    private String nom;
    private String prenom;
    private String email;
    private Date dateNaiss;
    private LIEU_NAISSANCE lieuNaiss;
    private SEXE sexe;
    private SituationFamiliale sf;

}
