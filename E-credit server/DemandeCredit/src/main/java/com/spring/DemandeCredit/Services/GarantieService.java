package com.spring.DemandeCredit.Services;

import com.spring.DemandeCredit.Entities.Garantie;
import com.spring.DemandeCredit.Repositories.GarantieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GarantieService {

    @Autowired
    private final GarantieRepository garantieRepository;

    public Garantie addGarantie(Garantie garantie) {
        return garantieRepository.save(garantie);     }


    public Garantie updateGarantie(Garantie garantie) {

        return garantieRepository.save(garantie);
    }

    public Boolean deleteGarantie(Long idG) {
        Optional<Garantie> garantieOptional = garantieRepository.findById(idG);
        if (garantieOptional.isPresent()) {
            garantieRepository.deleteById(idG);
            return true; // La suppression a été effectuée avec succès
        } else {
            return false; // L'identifiant spécifié n'existe pas
        }
    }

    public List<Garantie> getAllGarantie() {
        return (List<Garantie>) garantieRepository.findAll();
    }

    public Garantie getGarantieById(Long idG) {
        return garantieRepository.findById(idG).orElse(null);
    }
}
