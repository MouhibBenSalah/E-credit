package com.spring.DemandeCredit.Controllers;

import com.spring.DemandeCredit.Entities.DemandeCredit;
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
    public ResponseEntity<List<DemandeCredit>> findAllDemandesCredit(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(demandeCreditService.findAllDemandeCreditByUser(userId));
    }
}
