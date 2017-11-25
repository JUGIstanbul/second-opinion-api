package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.entity.Case;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1")
public class CaseController {


    @PostMapping(value="/cases")
    @ResponseBody
    public ResponseEntity save(@RequestBody Case aCase) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "/api/v1/cases/1");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
