package com.spring.DemandeCredit.Controllers;


import com.spring.DemandeCredit.Services.PdfNumericDataExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
public class PdfNumericDataExtractorController {
    @Autowired
    private PdfNumericDataExtractorService pdfNumericDataExtractorService;

    @PostMapping("/extract-numbers")
    public ResponseEntity<List<Double>> extractNumbers(@RequestParam("file") MultipartFile file) {
        try {
            List<Double> numbers = pdfNumericDataExtractorService.extractNumericData(file);
            return new ResponseEntity<>(numbers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
