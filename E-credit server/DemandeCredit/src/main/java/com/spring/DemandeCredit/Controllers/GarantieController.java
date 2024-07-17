package com.spring.DemandeCredit.Controllers;

import com.spring.DemandeCredit.Entities.Garantie;
import com.spring.DemandeCredit.Services.GarantieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/garantie")
@RestController
@RequiredArgsConstructor
public class GarantieController {
    private final GarantieService garantieService;

    @PostMapping("/add")
    public Garantie addGarantie(@RequestBody Garantie garantie) {

        return garantieService.addGarantie(garantie);

    }

    @PutMapping("/update")
    public Garantie updateGarantie(@RequestBody Garantie garantie) {

        return garantieService.updateGarantie(garantie);
    }


    @DeleteMapping("/delete/{idG}")
    public ResponseEntity<String> deleteGarantie(@PathVariable("idG") Long idG) {
        boolean deleted = garantieService.deleteGarantie(idG);
        if (deleted) {
            return ResponseEntity.ok("Garantie deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Garantie with ID " + idG + " does not exist.");
        }
    }

    @GetMapping("/get/{idG}")
    public Garantie getGarantieById(@PathVariable("idG") Long idG) {
        return garantieService.getGarantieById(idG);
    }


    @GetMapping("/getAllGarantie")
    public List<Garantie> getAllGarantie() {
        return ResponseEntity.ok().body(garantieService.getAllGarantie()).getBody();

    }

}
