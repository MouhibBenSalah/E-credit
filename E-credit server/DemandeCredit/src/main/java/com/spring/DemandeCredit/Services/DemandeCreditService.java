package com.spring.DemandeCredit.Services;

import com.spring.DemandeCredit.DTO.UserDTO;
import com.spring.DemandeCredit.Entities.DemandeCredit;
import com.spring.DemandeCredit.Enum.Statut;
import com.spring.DemandeCredit.Enum.TypeUnite;
import com.spring.DemandeCredit.Repositories.DemandeCreditRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class DemandeCreditService {

    @Autowired
    private DemandeCreditRepository demandeCreditRepository;

    private RestTemplate restTemplate;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 10; // Change the length as needed

    public  String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public List<DemandeCredit> getAllDemandeCredit(){
        return demandeCreditRepository.findAll();
    }

    public DemandeCredit createDemandeCredit(DemandeCredit demandeCredit){
        demandeCredit.setDateEntreeRelation(new Date());
        demandeCredit.setNumDemande(generateRandomString()); // Set the random string

        return demandeCreditRepository.save(demandeCredit);
    }

    public List<DemandeCredit> findAllDemandeCreditByUser(Long userId) {
        return demandeCreditRepository.findAllByUserId(userId);
    }
    public DemandeCredit addDemandeCredit(Long idU, DemandeCredit demandeCredit) {
        // Appel au service d'authentification pour vérifier si l'utilisateur existe
        String userServiceUrl = "http://localhost:4020/auth/login/" + idU;
        User user = restTemplate.getForObject(userServiceUrl, User.class);

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        // Assigner l'ID de l'utilisateur à la demande de crédit
        demandeCredit.setUserId(idU);

        // Sauvegarder la demande de crédit dans la base de données
        return demandeCreditRepository.save(demandeCredit);
    }

    public Float simulateur(Float montant, Integer duree, Float interet, TypeUnite unite) {
        // Calcul du taux d'intérêt annuel, trimestriel et semestriel à partir du taux mensuel
        float interetAnnuelle = (float) (Math.pow(1 + interet, 12) - 1);
        float interetTrimestriel = (float) (Math.pow(1 + interet, 3) - 1);
        float interetSemestrielle = (float) (Math.pow(1 + interet, 6) - 1);
        // Ajustement du taux d'intérêt et du nombre d'échéances en fonction de l'unité
        switch (unite) {
            case MENSUELLE:
                // Le taux d'intérêt est déjà mensuel, aucune conversion nécessaire
                break;
            case TRIMESTRIELLE:
                interet = interetTrimestriel;
                duree /= 3; // Convertir le nombre d'échéances en trimestrielles
                break;
            case SEMESTRIELLE:
                interet = interetSemestrielle;
                duree /= 6; // Convertir le nombre d'échéances en semestrielles
                break;
            case ANNUELLE:
                interet = interetAnnuelle;
                duree /= 12; // Convertir le nombre d'échéances en annuelles
                break;

        }
        // Calcul du montant total à payer en tenant compte de l'intérêt
        float montantTotal = montant * (1 + interet * duree);

        // Montant à payer par échéance
        return montantTotal / duree;
    }
    public Integer calculateNbreDemandes() {
        List<DemandeCredit> demandesCredit = demandeCreditRepository.findAll();
        int nbreDemandes = 0;
        for (DemandeCredit demandeCredit : demandesCredit) {
                nbreDemandes++;
        }
        return nbreDemandes;
    }
    public DemandeCredit updateStatus(Long id, Statut statut) {
        Optional<DemandeCredit> demandeCreditOptional = demandeCreditRepository.findById(id);
        if (demandeCreditOptional.isPresent()) {
            DemandeCredit demandeCredit = demandeCreditOptional.get();
            demandeCredit.setStatut(statut);
            return demandeCreditRepository.save(demandeCredit);
        } else {
            throw new RuntimeException("DemandeCredit not found with id " + id);
        }
    }



}
