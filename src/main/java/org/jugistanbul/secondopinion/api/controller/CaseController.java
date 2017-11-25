package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.service.CaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CaseController {

    private CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @PostMapping(value="/cases")
    @ResponseBody
    public ResponseEntity save(@RequestBody Case aCase) {
        return caseService.createCase(aCase);
    }

    @DeleteMapping(value = "/cases/{caseid}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("caseid") Long caseId){
        return caseService.deleteCase(caseId);
    }

}
