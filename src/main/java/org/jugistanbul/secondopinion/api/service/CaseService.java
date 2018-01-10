package org.jugistanbul.secondopinion.api.service;

import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.entity.Patient;
import org.jugistanbul.secondopinion.api.exception.EntityNotFoundException;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.jugistanbul.secondopinion.api.repository.PatientRepository;
import org.jugistanbul.secondopinion.api.service.validator.CaseValidator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CaseService {

    private CaseRepository caseRepository;

    private PatientRepository patientRepository;

    private CaseValidator caseValidator;

    public CaseService(CaseRepository caseRepository, CaseValidator caseValidator) {
        this.caseRepository = caseRepository;
        this.caseValidator = caseValidator;
    }

    public ResponseEntity createCase(Case aCase) {
        caseValidator.validate(aCase);
        Case saveCase = caseRepository.save(aCase);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/cases/" + saveCase.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    public ResponseEntity deleteCase(Long caseId) {
        Case tempCase = caseRepository.findOne(caseId);
        if (tempCase == null)
            throw new EntityNotFoundException("entity.notFound");
        tempCase.setModelStatus(ModelStatus.DELETED);
        caseRepository.save(tempCase);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity getCaseById(Long caseId) {
        Case tempCase = caseRepository.findOne(caseId);
        if (tempCase == null)
            throw new EntityNotFoundException("entity.notFound");
        return new ResponseEntity<>(tempCase, HttpStatus.OK);
    }

    public ResponseEntity updateCase(Long caseId, Case createdCase) {
        Case tempCase = caseRepository.findOne(caseId);
        if (tempCase == null)
            throw new EntityNotFoundException("entity.notFound");
        caseValidator.validate(createdCase);
        createdCase.setId(tempCase.getId());
        caseRepository.save(createdCase);
        return new ResponseEntity<>(createdCase, HttpStatus.OK);
    }


    public ResponseEntity getPatientCases(Long patientId) {

        List<Case> patientCases =caseRepository.findByPatientId(patientId);

        patientCases = patientCases == null? Collections.emptyList():patientCases;

        return  new ResponseEntity<>(patientCases, HttpStatus.OK);

    }
}
