package com.spring.DemandeCredit.Services;

import com.spring.DemandeCredit.Entities.DemandeCredit;
import com.spring.DemandeCredit.Entities.PieceJointe;
import com.spring.DemandeCredit.Repositories.DemandeCreditRepository;
import com.spring.DemandeCredit.Repositories.PieceJointeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class PieceJointeService {
    private final PieceJointeRepository pieceJointeRepository;
    private final DemandeCreditRepository demandeCreditRepository;


    public void savePieceJointe(MultipartFile file, boolean obligatoire) throws Exception {

        PieceJointe pieceJointe = new PieceJointe();
        pieceJointe.setNomFichier(file.getOriginalFilename());
        pieceJointe.setTypeMime(file.getContentType());
        pieceJointe.setTaille(file.getSize());
        pieceJointe.setData(file.getBytes());
        pieceJointe.setObligatoire(obligatoire);

        // Save PieceJointe
        pieceJointeRepository.save(pieceJointe);


    }
}
