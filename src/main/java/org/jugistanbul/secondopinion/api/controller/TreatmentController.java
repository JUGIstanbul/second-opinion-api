package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.entity.Treatment;
import org.jugistanbul.secondopinion.api.service.TreatmentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/v1/treatments")
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @PostMapping()
    public ResponseEntity save(HttpServletRequest httpServletRequest, @RequestBody Treatment treatment) {

        Treatment savedTreatment = treatmentService.save(treatment);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, UrlHelper.getFullRequestUrl(httpServletRequest) + "/"
               + savedTreatment.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        treatmentService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Treatment treatment) {

        treatmentService.update(id, treatment);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public Treatment get(@PathVariable("id") Long id) {

        return treatmentService.getById(id);
    }


}
