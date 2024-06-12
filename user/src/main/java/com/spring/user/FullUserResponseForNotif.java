package com.spring.user;

import com.spring.user.DTO.NotificationDTO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullUserResponseForNotif {
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
    List<NotificationDTO> notifications;
}
