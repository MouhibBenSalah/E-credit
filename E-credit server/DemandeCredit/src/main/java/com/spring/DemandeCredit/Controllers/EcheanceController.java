package com.spring.DemandeCredit.Controllers;

import com.spring.DemandeCredit.Entities.Echeance;
import com.spring.DemandeCredit.Services.EcheanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/echeances")
@RestController
public class EcheanceController {
    @Autowired
    private EcheanceService echeanceService;

    @PostMapping
    public Echeance createEcheance(@RequestBody Echeance echeance) {
        return echeanceService.saveEcheance(echeance);
    }

    @PutMapping("/{id}")
    public Echeance updateEcheance(@PathVariable Long id, @RequestBody Echeance echeance) {
        echeance.setIdE(id);
        return echeanceService.updateEcheance(echeance);
    }

    @DeleteMapping("/{id}")
    public void deleteEcheance(@PathVariable Long id) {
        echeanceService.deleteEcheance(id);
    }

    @GetMapping("/{id}")
    public Echeance getEcheanceById(@PathVariable Long id) {
        return echeanceService.getEcheanceById(id);
    }

    @GetMapping
    public List<Echeance> getAllEcheances() {
        return echeanceService.getAllEcheances();
    }

}
