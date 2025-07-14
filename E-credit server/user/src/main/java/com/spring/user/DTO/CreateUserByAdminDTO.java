package com.spring.user.DTO;

import com.spring.user.Enum.Role;
import com.spring.user.Enum.LIEU_NAISSANCE;
import com.spring.user.Enum.SEXE;
import com.spring.user.Enum.SituationFamiliale;
import lombok.Data;

import java.util.Date;

@Data
public class CreateUserByAdminDTO {
    private String nom;
    private String prenom;
    private Long numCin;
    private Date dateNaiss;
    private LIEU_NAISSANCE lieuNaiss;
    private SEXE sexe;
    private SituationFamiliale sf;
    private String email;
    private Role targetRole; // CHEF_AGENCE or CHARGE_BANQUE
    private float revenuMensuel;
    private float chargesMensuelles;
    private float salaire;
}