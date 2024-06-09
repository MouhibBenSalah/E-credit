package com.spring.DemandeCredit.Repositories;

import com.spring.DemandeCredit.Entities.DemandeCredit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeCreditRepository extends JpaRepository<DemandeCredit,Integer> {
}
