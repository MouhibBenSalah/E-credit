package com.spring.DemandeCredit.Services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class PdfNumericDataExtractorService {
    public List<Double> extractNumericData(MultipartFile file) throws IOException {
        List<Double> numbers = new ArrayList<>();
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                String numberStr = matcher.group();
                try {
                    double number = Double.parseDouble(numberStr);
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    System.err.println("Erreur de conversion pour : " + numberStr);
                }
            }
        }
        return numbers;
    }
}
