package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CaseController {

    private CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @PostMapping(value = "/cases")
    @ResponseBody
    public ResponseEntity save(@RequestBody Case aCase) {
        return caseService.createCase(aCase);
    }

    @GetMapping(value = "/cases")
    @ResponseBody
    public ResponseEntity save(@RequestParam("patientId") Long patientId) {
        return caseService.getPatientCases(patientId);
    }

    @DeleteMapping(value = "/cases/{caseid}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("caseid") Long caseId) {
        return caseService.deleteCase(caseId);
    }

    @GetMapping(value = "/cases/{caseid}")
    @ResponseBody
    public ResponseEntity getCaseById(@PathVariable("caseid") Long caseId) {
        return caseService.getCaseById(caseId);
    }

    @PutMapping(value = "/cases/{caseid}")
    @ResponseBody
    public ResponseEntity getCaseById(@PathVariable("caseid") Long caseId, @RequestBody Case aCase) {
        return caseService.updateCase(caseId, aCase);
    }

}
