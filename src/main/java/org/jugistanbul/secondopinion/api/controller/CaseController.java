package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.service.CaseService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Case aCase) {
        caseService.createCase(aCase);
    }


}
