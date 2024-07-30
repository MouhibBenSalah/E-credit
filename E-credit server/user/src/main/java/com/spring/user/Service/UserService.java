package com.spring.user.Service;


import com.spring.user.DTO.UpdateUserDTO;
import com.spring.user.Entity.ChangePasswordRequest;
import com.spring.user.Entity.Compte;
import com.spring.user.Entity.User;
import com.spring.user.Enum.Role;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
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
    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getAllClients() {
        return  userRepository.findByRole(Role.Client);
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

    public Boolean deleteUser(Long idU) {
        Optional<User> UserOptional = userRepository.findById(idU);
        if (UserOptional.isPresent()) {
            userRepository.deleteById(idU);
            return true; // La suppression a été effectuée avec succès
        } else {
            return false; // L'identifiant spécifié n'existe pas
        }
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

    public User updateUser(Long userId, UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (updateUserDTO.getNom() != null) {
                user.setNom(updateUserDTO.getNom());
            }
            if (updateUserDTO.getPrenom() != null) {
                user.setPrenom(updateUserDTO.getPrenom());
            }
            if (updateUserDTO.getEmail() != null) {
                user.setEmail(updateUserDTO.getEmail());
            }
            if (updateUserDTO.getDateNaiss() != null) {
                user.setDateNaiss(updateUserDTO.getDateNaiss());
            }
            if (updateUserDTO.getLieuNaiss() != null) {
                user.setLieuNaiss(updateUserDTO.getLieuNaiss());
            }
            if (updateUserDTO.getSexe() != null) {
                user.setSexe(updateUserDTO.getSexe());
            }
            if (updateUserDTO.getSf() != null) {
                user.setSf(updateUserDTO.getSf());
            }
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
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


    public Integer calculateNbreClients() {
        List<User> users = userRepository.findAll();
        int nbreClients = 0;
        for (User user : users) {
            if (Role.Client.equals(user.getRole())) {
                nbreClients++;
            }
        }
        return nbreClients;
    }

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }
}
