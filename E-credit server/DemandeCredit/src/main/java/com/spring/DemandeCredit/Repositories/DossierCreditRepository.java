package com.spring.DemandeCredit.Repositories;

import com.spring.DemandeCredit.Entities.DossierCredit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierCreditRepository extends JpaRepository<DossierCredit, Long> {
}
