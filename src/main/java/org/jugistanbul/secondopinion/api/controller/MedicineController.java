package org.jugistanbul.secondopinion.api.controller;

import org.jugistanbul.secondopinion.api.entity.Medicine;
import org.jugistanbul.secondopinion.api.service.MedicineService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/v1/medicine")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping()
    public ResponseEntity save(HttpServletRequest httpServletRequest, @RequestBody Medicine medicine) {

        Medicine savedTreatment = medicineService.save(medicine);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, UrlHelper.getFullRequestUrl(httpServletRequest) + "/"
                + savedTreatment.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public Medicine get(@PathVariable("id") Long id) {
        return medicineService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {

        medicineService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody Medicine medicine) {

        medicineService.update(id, medicine);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
