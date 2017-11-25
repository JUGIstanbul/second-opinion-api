package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/v1")
public class CaseController {

    @Autowired
    CaseRepository caseRepository;

    @PostMapping(value="/cases")
    @ResponseBody
    public ResponseEntity save(@RequestBody Case aCase) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/cases/1");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/cases/{caseid}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("caseid") Long caseId){
        Case tempCase= caseRepository.findOne(caseId);
        tempCase.setModelStatus(ModelStatus.DELETED);
        caseRepository.save(tempCase);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
