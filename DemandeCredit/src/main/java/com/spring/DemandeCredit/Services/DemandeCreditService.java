package com.spring.DemandeCredit.Services;

import com.spring.DemandeCredit.Entities.DemandeCredit;
import com.spring.DemandeCredit.Repositories.DemandeCreditRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DemandeCreditService {

    @Autowired
    private DemandeCreditRepository demandeCreditRepository;

    public List<DemandeCredit> getAllDemandeCredit(){
        return demandeCreditRepository.findAll();
    }

    public DemandeCredit createDemandeCredit(DemandeCredit demandeCredit){
        return demandeCreditRepository.save(demandeCredit);
    }

}
