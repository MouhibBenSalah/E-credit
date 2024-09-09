package com.spring.DemandeCredit.Controllers;

import com.spring.DemandeCredit.Entities.Echeance;
import com.spring.DemandeCredit.Services.EcheanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @PostMapping("/generateEcheances")
    public ResponseEntity<List<Echeance>> generateEcheances(
            @RequestParam float montant,
            @RequestParam int duree,
            @RequestParam String typeCredit) {

        List<Echeance> echeances = new ArrayList<>(echeanceService.genererEcheances(montant, duree, typeCredit));
        return ResponseEntity.ok(echeances);
    }

}
