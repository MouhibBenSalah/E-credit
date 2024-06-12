package com.spring.DemandeCredit.Repositories;

import com.spring.DemandeCredit.Entities.DemandeCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeCreditRepository extends JpaRepository<DemandeCredit,Integer> {
    List<DemandeCredit> findAllByUserId(Integer userId);
}
