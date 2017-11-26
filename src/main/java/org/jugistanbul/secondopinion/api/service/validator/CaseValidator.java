package org.jugistanbul.secondopinion.api.service.validator;

import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.entity.Treatment;
import org.jugistanbul.secondopinion.api.exception.EntityValidationException;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.springframework.stereotype.Component;

@Component
public class CaseValidator {

    private CaseRepository caseRepository;

    public CaseValidator(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public void validate(Case theCase) {
        if (theCase == null) {
            throw new EntityValidationException("case.null");
        }
        if (theCase.getId() == null) {
            throw new EntityValidationException("case.null");
        }
        Long caseId = theCase.getId();
        if (caseRepository.findOne(caseId) == null) {
            throw new EntityValidationException("case.notFound");
        }
        if (theCase.getNickname() == null) {
            throw new EntityValidationException("entity.notFound");
        }
        if (theCase.getIllnessStartDate() == null) {
            throw new EntityValidationException("entity.notFound");
        }
        if (theCase.getSymptoms() == null) {
            throw new EntityValidationException("entity.notFound");
        }

    }
}
