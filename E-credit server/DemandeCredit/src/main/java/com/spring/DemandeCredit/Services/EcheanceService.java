package com.spring.DemandeCredit.Services;

import com.spring.DemandeCredit.Entities.Echeance;
import com.spring.DemandeCredit.Repositories.ContratCreditRepository;
import com.spring.DemandeCredit.Repositories.EcheanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EcheanceService {

    @Autowired
    private EcheanceRepository echeanceRepoistory;
    private ContratCreditRepository contratCreditRepository;

    public Echeance saveEcheance(Echeance echeance) {
        return echeanceRepoistory.save(echeance);
    }

    public Echeance updateEcheance(Echeance echeance) {
        return echeanceRepoistory.save(echeance);
    }

    public void deleteEcheance(Long id) {
        echeanceRepoistory.deleteById(id);
    }

    public Echeance getEcheanceById(Long id) {
        return echeanceRepoistory.findById(id).orElse(null);
    }

    public List<Echeance> getAllEcheances() {
        return echeanceRepoistory.findAll();
    }

    public List<Echeance> genererEcheances(float montant, int duree, String typeCredit) {
        // Map typeCredit to interet
        float interet;
        switch (typeCredit) {
            case "AutoOccasion":
                interet = 0.058f;
                break;
            case "Personnel":
                interet = 0.06f;
                break;
            case "Amenagement":
                interet = 0.049f;
                break;
            case "AutoNeuve":
                interet = 0.059f;
                break;
            default:
                throw new IllegalArgumentException("Invalid type of credit");
        }

        // Calcul de la mensualité en utilisant la formule de l'annuité
        float mensualite = (montant * interet) / (1 - (float) Math.pow(1 + interet, -duree));

        Calendar calendar = Calendar.getInstance();

        // Création des échéances
        Set<Echeance> nouvellesEcheances = new HashSet<>();
        float capitalRestant = montant;

        for (int i = 0; i < duree; i++) {
            Echeance echeance = new Echeance();
            // Définir le dernier jour du mois courant
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            echeance.setDatePaiement(calendar.getTime()); // Date de paiement de l'échéance

            float interetsPayes = capitalRestant * interet; // Intérêts payés pour cette échéance
            float capitalRembourse = mensualite - interetsPayes; // Capital remboursé pour cette échéance

            // Mise à jour du capital restant dû
            capitalRestant -= capitalRembourse;

            echeance.setCapitalRembourse(capitalRembourse); // Capital remboursé
            echeance.setCapitalRestantDu(capitalRestant); // Capital restant dû
            echeance.setInteretsPayes(interetsPayes); // Intérêts payés
            echeance.setMensualite(mensualite); // Mensualité

            nouvellesEcheances.add(echeance);

            // Passage à la même date du mois suivant
            calendar.add(Calendar.MONTH, 1);
        }

        // Convert to list and sort by datePaiement
        List<Echeance> sortedEcheances = new ArrayList<>(nouvellesEcheances);
        sortedEcheances.sort(Comparator.comparing(Echeance::getDatePaiement));

        return sortedEcheances;
    }
}

