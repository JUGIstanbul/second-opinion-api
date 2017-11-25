package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1")
public class CaseController {

    @Autowired
    private CaseRepository caseRepository;


    @PostMapping(value="/cases")
    @ResponseBody
    public ResponseEntity save(@RequestBody Case aCase) {
        Case saveCase = caseRepository.save(aCase);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/cases/"+saveCase.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
