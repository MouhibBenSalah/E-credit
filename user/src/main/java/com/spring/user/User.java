package com.spring.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

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

    private String email;
    private String password;

}
