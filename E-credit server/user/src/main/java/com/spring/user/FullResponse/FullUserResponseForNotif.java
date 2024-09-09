package com.spring.user.FullResponse;

import com.spring.user.DTO.NotificationDTO;
import com.spring.user.Enum.Role;
import com.spring.user.Enum.SituationFamiliale;
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
    List<NotificationDTO> notifications;
}
