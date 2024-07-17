package com.spring.DemandeCredit.Services;

import com.spring.DemandeCredit.Entities.Echeance;
import com.spring.DemandeCredit.Repositories.ContratCreditRepository;
import com.spring.DemandeCredit.Repositories.EcheanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.List;

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
}
