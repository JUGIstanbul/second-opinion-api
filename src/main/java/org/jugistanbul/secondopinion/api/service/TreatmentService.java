package org.jugistanbul.secondopinion.api.service;

import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.entity.Treatment;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.jugistanbul.secondopinion.api.repository.TreatmentRepository;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {

    private TreatmentRepository treatmentRepository;

    private CaseRepository caseRepository;

    public TreatmentService(TreatmentRepository treatmentRepository, CaseRepository caseRepository) {
        this.treatmentRepository = treatmentRepository;
        this.caseRepository = caseRepository;
    }

    public Treatment save(Treatment treatment) throws EntityValidationException {

        if (treatment.getRelevantCase() == null) {
            throw new EntityValidationException("case", "case.null");
        }
        if (treatment.getRelevantCase().getId() == null) {
            throw new EntityValidationException("case.id", "case_id.null");
        }
        Long caseId = treatment.getRelevantCase().getId();
        if (caseRepository.findOne(caseId) == null) {
            throw new EntityValidationException("case", "case.not_found");
        }
        return treatmentRepository.save(treatment);
    }

    public void delete(Long id) throws EntityNotFoundException {
        Treatment treatment = treatmentRepository.findOne(id);

        if (treatment == null)
            throw new EntityNotFoundException();

        treatment.setModelStatus(ModelStatus.DELETED);
        treatmentRepository.save(treatment);
    }
}
