package org.jugistanbul.secondopinion.api.service.validator;

import org.jugistanbul.secondopinion.api.entity.Medicine;
import org.jugistanbul.secondopinion.api.entity.Treatment;
import org.jugistanbul.secondopinion.api.exception.EntityValidationException;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.springframework.stereotype.Component;

@Component
public class MedicineValidator {
    private CaseRepository caseRepository;

    public MedicineValidator(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public void validate(Medicine medicine) {
        if (medicine.getRelevantCase() == null) {
            throw new EntityValidationException("case.null");
        }
        if (medicine.getRelevantCase().getId() == null) {
            throw new EntityValidationException("caseId.null");
        }
        Long caseId = medicine.getRelevantCase().getId();
        if (caseRepository.findOne(caseId) == null) {
            throw new EntityValidationException("case.notFound");
        }
        if (medicine.getName() == null){
            throw new EntityValidationException("medicine.name");
        }
    }
}
