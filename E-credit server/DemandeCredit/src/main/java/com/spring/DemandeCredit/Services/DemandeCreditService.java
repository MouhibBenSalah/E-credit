package com.spring.DemandeCredit.Services;

import com.spring.DemandeCredit.Entities.DemandeCredit;
import com.spring.DemandeCredit.Enum.TypeUnite;
import com.spring.DemandeCredit.Repositories.DemandeCreditRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class DemandeCreditService {

    @Autowired
    private DemandeCreditRepository demandeCreditRepository;

    private RestTemplate restTemplate;

    public List<DemandeCredit> getAllDemandeCredit(){
        return demandeCreditRepository.findAll();
    }

    public DemandeCredit createDemandeCredit(DemandeCredit demandeCredit){
        return demandeCreditRepository.save(demandeCredit);
    }

    public List<DemandeCredit> findAllDemandeCreditByUser(Long userId) {
        return demandeCreditRepository.findAllByUserId(userId);
    }
    public DemandeCredit addDemandeCredit(Long idUser, DemandeCredit demandeCredit) {
        // Appel au service d'authentification pour vérifier si l'utilisateur existe
        String userServiceUrl = "http://localhost:4020/login/" + idUser;

        User user = restTemplate.getForObject(userServiceUrl, User.class);

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        // Assigner l'ID de l'utilisateur à la demande de crédit
        demandeCredit.setUserId(idUser);

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

}
