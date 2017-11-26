package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/cities")
public class CityController {

    @Autowired
    public CityRepository cityRepository;

    @GetMapping
    public ResponseEntity<?> getAllCities() {
        return new ResponseEntity<>(cityRepository.findAll(), HttpStatus.OK);
    }
}
