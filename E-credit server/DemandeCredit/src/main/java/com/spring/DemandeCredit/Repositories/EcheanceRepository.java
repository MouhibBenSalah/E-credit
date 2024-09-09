package com.spring.DemandeCredit.Repositories;

import com.spring.DemandeCredit.Entities.Echeance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EcheanceRepository extends JpaRepository<Echeance, Long> {
}
