package com.spring.DemandeCredit.Controllers;

import com.spring.DemandeCredit.Entities.ContratCredit;
import com.spring.DemandeCredit.Entities.Echeance;
import com.spring.DemandeCredit.Enum.TypeUnite;
import com.spring.DemandeCredit.Services.ContratCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequestMapping("/ContratCredit")
@RestController
@RequiredArgsConstructor
public class ContratCreditController {

    private final ContratCreditService contratCreditService;

    @PostMapping("/creer-et-affecter/{idDemande}")
    public ResponseEntity<ContratCredit> creerContratEtAffecterADemande(
            @PathVariable("idDemande") Long idDemande,
            @RequestBody ContratCredit contratCredit) {

        ContratCredit contratSaved = contratCreditService.creerContratEtAffecterADemande(idDemande, contratCredit);

        return ResponseEntity.ok(contratSaved);
    }

    @PutMapping("/update")
    public ContratCredit updateContratCredit(@RequestBody ContratCredit contratCredit) {

        return contratCreditService.updateContratCredit(contratCredit);
    }


    @DeleteMapping("/delete/{idC}")
    public ResponseEntity<String> deleteContratCredit(@PathVariable("idC") Long idC) {
        boolean deleted = contratCreditService.deleteContratCredit(idC);
        if (deleted) {
            return ResponseEntity.ok("ContratCredit deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ContratCredit with ID " + idC + " does not exist.");
        }
    }

    @GetMapping("/get/{idC}")
    public ContratCredit getContratCreditById(@PathVariable("idC") Long idC) {
        return contratCreditService.getContratCreditById(idC);
    }


    @GetMapping("/getAllContratCredit")
    public List<ContratCredit> getAllContratCredit() {
        return ResponseEntity.ok().body(contratCreditService.getAllContratCredit()).getBody();

    }


    @GetMapping("/{idC}")
    public ResponseEntity<byte[]> generateContratPdf(@PathVariable("idC") Long idC) {
        try {
            byte[] pdfBytes = contratCreditService.generateContratPdf(idC);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "contrat.pdf");
            headers.setContentLength(pdfBytes.length);
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/process")
    public ResponseEntity<String> processDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam Map<String, String> replacementMap) {
        try {
            // Appel à votre service pour traiter le document
            InputStream inputStream = file.getInputStream();
            String outputPdfPath = "output_document.pdf";
            contratCreditService.processWordDocument(inputStream, outputPdfPath, replacementMap);
            return ResponseEntity.ok("Document processed successfully. Output PDF saved at " + outputPdfPath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing document: " + e.getMessage());
        }
    }


   /* @PostMapping("/{contratId}/echeances/{echeanceId}")
    public ResponseEntity<ContratCredit> assignEcheanceToContrat(
            @PathVariable Long contratId,
            @PathVariable Long echeanceId) {
        ContratCredit contratCredit = iContratCreditService.assignEcheanceToContrat(contratId, echeanceId);
        return ResponseEntity.ok().body(contratCredit);
    }*/

    @PostMapping("/{idC}/generer-echeances")
    public ResponseEntity<Set<Echeance>> genererEtAssignerEcheances(
            @PathVariable Long idC,
            @RequestParam float montant,
            @RequestParam int duree,
            @RequestParam float interet,
            @RequestParam TypeUnite unite) {

        Set<Echeance> echeances = contratCreditService.genererEcheances(montant, duree, interet, unite, idC);

        return ResponseEntity.ok().body(echeances);
    }
}
