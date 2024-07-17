package com.spring.user.LoanEvaluation;

import com.spring.user.DTO.DemandeCreditDTO;
import com.spring.user.Entity.Compte;
import com.spring.user.Entity.User;
import com.spring.user.Enum.EmploymentType;
import com.spring.user.Enum.EtatCompte;
import org.springframework.stereotype.Service;

@Service
public class LoanEvaluationService {

    public double calculateScore(User user) {
        // Calcul des scores individuels pour revenuMensuel, salaire et chargesMensuelles
        double scoreRevenuMensuel;
        double scoreSalaire;
        double scoreChargesMensuelles;

        // Définir les seuils et les scores pour revenuMensuel
        if (user.getRevenuMensuel() < 1000) {
            scoreRevenuMensuel = 1;
        } else if (user.getRevenuMensuel() >= 1000 && user.getRevenuMensuel() < 3000) {
            scoreRevenuMensuel = 2.5;
        } else {
            scoreRevenuMensuel = 3.5;
        }

        // Définir les seuils et les scores pour salaire
        if (user.getSalaire() < 1500) {
            scoreSalaire = 1;
        } else if (user.getSalaire() >= 1500 && user.getSalaire() < 2500) {
            scoreSalaire = 2.5;
        } else {
            scoreSalaire = 3.5;
        }

        // Définir les seuils et les scores pour chargesMensuelles
        if (user.getChargesMensuelles() < 500) {
            scoreChargesMensuelles = 3.5;
        } else if (user.getChargesMensuelles() >= 500 && user.getChargesMensuelles() < 2000) {
            scoreChargesMensuelles = 2.5;
        } else {
            scoreChargesMensuelles = 1;
        }

        // Calcul du revenu net score
        double revenuNetScore = scoreRevenuMensuel + scoreSalaire - scoreChargesMensuelles;
        // Calcul des autres scores
        double ageScore = calculateAgeScore(user.getAge());
        double employmentScore = calculateEmploymentScore(user.getEmploymentType());

        // Poids des critères
        double w1 = 0.4;
        double w2 = 0.2;
        double w3 = 0.6;

        // Calcul du score total
        return w1 * ageScore + w2 * employmentScore + w3 * revenuNetScore;
    }

    public String evaluateRisk(Compte compteBancaire, DemandeCreditDTO demandeCredit ) {
        double riskScore = 0.0;
        // Calculer le risque basé sur l'état du compte
        if (compteBancaire.getEtatDeCompte() == EtatCompte.NEGATIF) {
            riskScore += 50; // Poids pour un état de compte négatif
        }
        riskScore += compteBancaire.getNombreDeRetardDePaiement() * 0.5; // Poids pour le nombre de retards

        riskScore += (demandeCredit.getMontant() /100 )* 0.4; // Poids pour le nombre de retards

        if (riskScore < 200) {
            return "Faible";
        } else if (riskScore >= 200 && riskScore < 300) {
            return "Modéré";
        } else {
            return "Élevé";
        }
    }


    public String approveCredit(User user, Compte compteBancaire, DemandeCreditDTO demandeCredit) {
        double score = calculateScore(user);
        String riskLevel = evaluateRisk( compteBancaire, demandeCredit);

        // Logique de décision pour approuver ou non le crédit
        if (score > 70 && riskLevel.equals("Faible")) {
            return "Crédit approuvé";
        } else if (score > 640 && riskLevel.equals("Modéré")) {
            return "Crédit approuvé avec conditions";
        } else {
            return "Crédit refusé";
        }
    }


    public double calculateAgeScore(Integer age) {
        return 100 - age;
    }

    public double calculateEmploymentScore(EmploymentType employmentType) {
        return switch (employmentType) {
            case PUBLIC -> 90;
            case PRIVATE -> 80;
            case SELF_EMPLOYED -> 60;
        };
    }
}
