package org.jugistanbul.secondopinion.api.service;

import java.util.List;

import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.entity.CaseStatus;
import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CaseService {

    private CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public ResponseEntity createCase(Case aCase) {
        Case saveCase = caseRepository.save(aCase);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/cases/" + saveCase.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    public ResponseEntity deleteCase(Long caseId) {
        Case tempCase = caseRepository.findOne(caseId);
        tempCase.setModelStatus(ModelStatus.DELETED);
        caseRepository.save(tempCase);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity getCaseById(Long caseId) {
        Case tempCase = caseRepository.findOne(caseId);
        return new ResponseEntity<>(tempCase,HttpStatus.OK);
    }

    public ResponseEntity putCase(Long caseId,Case createdCase){
        createdCase.setId(caseId);
        caseRepository.save(createdCase);
        return new ResponseEntity<>(createdCase,HttpStatus.OK);
    }
    
    public List<Case> getUnclosedCases() {
        List<Case> unclosedCases = caseRepository.findByStatusNot(CaseStatus.CLOSED);
        return unclosedCases;
    }

}
