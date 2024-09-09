package com.spring.user.LoanEvaluation;

import com.spring.user.DTO.DemandeCreditDTO;
import com.spring.user.Entity.Compte;
import com.spring.user.Entity.User;
import lombok.Data;

@Data
public class LoanEvaluationRequest {
    private User user;
    private Compte compteBancaire;
    private DemandeCreditDTO demandeCredit;
}
