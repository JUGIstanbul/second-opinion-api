package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/universities")
public class University {

    @Autowired
    public UniversityRepository universityRepository;

    @GetMapping
    public ResponseEntity<?> getAllHospitals() {
        return new ResponseEntity<>(universityRepository.findAll(), HttpStatus.OK);
    }
}
