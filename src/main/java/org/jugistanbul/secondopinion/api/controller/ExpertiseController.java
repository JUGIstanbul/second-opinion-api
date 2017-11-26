package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.repository.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/expertise")
public class ExpertiseController {

    @Autowired
    public ExpertiseRepository expertiseRepository;

    @GetMapping
    public ResponseEntity<?> getAllExpertise() {
        return new ResponseEntity<Object>(expertiseRepository.findAll(), HttpStatus.OK);
    }
}
