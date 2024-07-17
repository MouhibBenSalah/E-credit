package com.spring.user.Service;


import com.spring.user.Entity.Compte;
import com.spring.user.Entity.User;
import com.spring.user.FullResponse.FullUserResponse;
import com.spring.user.FullResponse.FullUserResponseForNotif;
import com.spring.user.Repository.CompteRepository;
import com.spring.user.Repository.UserRepository;
import com.spring.user.Client.DemandeCreditClient;
import com.spring.user.Client.NotificationClient;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final CompteRepository compteRepository;
    @Autowired
    private UserRepository userRepository;
    private DemandeCreditClient demandeCreditclient;
    private NotificationClient notificationClient;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Compte creerComptePourClient(Long userId, Compte compte) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        compte.setUser(user);
        user.getComptes().add(compte);
        return compteRepository.save(compte);
    }
    public User getUserById(Long userId) {

        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User getUserByCin(Long cin) {
        return userRepository.findByNumCin(cin)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public FullUserResponse findUserbyDemandeCredit(Long id) {
        var user = userRepository.findById(id).orElse(
                User.builder().nom("not found").prenom("not found").build()
        );
        var demandesCredit= demandeCreditclient.findAllDemandesCreditByUser(id);
        return FullUserResponse.builder()
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .role(user.getRole())
                .numCin(user.getNumCin())
                .dateNaiss(user.getDateNaiss())
                .sf(user.getSf())
                .demandesCredit(demandesCredit)
                .build();
    }

    public FullUserResponseForNotif findUserbyNotif(Long id) {
        var user = userRepository.findById(id).orElse(
                User.builder().nom("not found").prenom("not found").build()
        );
        var notifications= notificationClient.findAllNotificationByUser(id);
        return FullUserResponseForNotif.builder()
                .nom(user.getNom())
                .notifications(notifications)
                .build();
    }
    @Transactional
    public Optional<User> updateUser(Long userId, User updatedUserData) {
        return userRepository.findById(userId).map(existingUser -> {
            if (updatedUserData.getNom() != null) {
                existingUser.setNom(updatedUserData.getNom());
            }
            if (updatedUserData.getPrenom() != null) {
                existingUser.setPrenom(updatedUserData.getPrenom());
            }
            if (updatedUserData.getDateNaiss() != null) {
                existingUser.setDateNaiss(updatedUserData.getDateNaiss());
            }
            if (updatedUserData.getLieuNaiss() != null) {
                existingUser.setLieuNaiss(updatedUserData.getLieuNaiss());
            }
            if (updatedUserData.getSexe() != null) {
                existingUser.setSexe(updatedUserData.getSexe());
            }
            if (updatedUserData.getSf() != null) {
                existingUser.setSf(updatedUserData.getSf());
            }
            if (updatedUserData.getEmail() != null) {
                existingUser.setEmail(updatedUserData.getEmail());
            }
            userRepository.save(existingUser);
            return existingUser;
        });
    }
    public double CalculRatioDendettement(User user) {
        double chargesMensuelles = user.getChargesMensuelles();
        double revenuMensuel = user.getRevenuMensuel();
        double salaire = user.getSalaire();
        return (chargesMensuelles / revenuMensuel + salaire) * 100;
    }

    public double calculateRepaymentCapacity(User user) {
        double chargesMensuelles = user.getChargesMensuelles();
        double revenuMensuel = user.getRevenuMensuel();
        double salaire = user.getSalaire();
        return revenuMensuel + salaire - chargesMensuelles;
    }


}
