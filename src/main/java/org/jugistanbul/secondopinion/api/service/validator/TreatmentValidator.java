package org.jugistanbul.secondopinion.api.service.validator;

import org.jugistanbul.secondopinion.api.entity.Treatment;
import org.jugistanbul.secondopinion.api.exception.EntityValidationException;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.springframework.stereotype.Component;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Component
public class TreatmentValidator {

    private CaseRepository caseRepository;

    public TreatmentValidator(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public void validate(Treatment treatment) {
        if (treatment.getRelevantCase() == null) {
            throw new EntityValidationException("case.null");
        }
        if (treatment.getRelevantCase().getId() == null) {
            throw new EntityValidationException("caseId.null");
        }
        Long caseId = treatment.getRelevantCase().getId();
        if (caseRepository.findOne(caseId) == null) {
            throw new EntityValidationException("case.notFound");
        }
    }

}
