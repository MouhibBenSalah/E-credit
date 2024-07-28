package com.spring.DemandeCredit.Controllers;

import com.spring.DemandeCredit.Entities.DemandeCredit;
import com.spring.DemandeCredit.Enum.TypeUnite;
import com.spring.DemandeCredit.Services.DemandeCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DemandesCredit")
public class DemandeCreditController {
    @Autowired
    private DemandeCreditService demandeCreditService;

    @GetMapping
    public List<DemandeCredit> getDemandeCredit() {
        return demandeCreditService.getAllDemandeCredit();
    }

    @PostMapping
    public DemandeCredit createDemande(@RequestBody DemandeCredit DC) {
        return demandeCreditService.createDemandeCredit(DC);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<DemandeCredit>> findAllDemandesCredit(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(demandeCreditService.findAllDemandeCreditByUser(userId));
    }

    @GetMapping("/simulate")
    public Float simulate(
            @RequestParam Float montant,
            @RequestParam Integer duree,
            @RequestParam Float interet,
            @RequestParam TypeUnite unite) {
        return demandeCreditService.simulateur(montant, duree, interet, unite);
    }



    @PostMapping("/user/{idU}")
    public ResponseEntity<DemandeCredit> createDemandeCredit(@PathVariable("idU") Long idU, @RequestBody DemandeCredit demandeCredit) {
        DemandeCredit createdDemandeCredit = demandeCreditService.addDemandeCredit(idU, demandeCredit);
        return ResponseEntity.ok(createdDemandeCredit);
    }
    @GetMapping("/nbreDemandes")
    public ResponseEntity<Integer> getNbreDemandes() {
        Integer nbreDemandes = demandeCreditService.calculateNbreDemandes();
        return ResponseEntity.ok(nbreDemandes);
    }


}
