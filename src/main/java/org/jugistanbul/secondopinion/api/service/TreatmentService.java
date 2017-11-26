package org.jugistanbul.secondopinion.api.service;

import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.entity.Treatment;
import org.jugistanbul.secondopinion.api.exception.EntityNotFoundException;
import org.jugistanbul.secondopinion.api.exception.EntityValidationException;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.jugistanbul.secondopinion.api.repository.TreatmentRepository;
import org.jugistanbul.secondopinion.api.service.validator.TreatmentValidator;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {

    private TreatmentRepository treatmentRepository;

    private TreatmentValidator treatmentValidator;

    public TreatmentService(TreatmentRepository treatmentRepository, TreatmentValidator treatmentValidator) {
        this.treatmentRepository = treatmentRepository;
        this.treatmentValidator = treatmentValidator;
    }

    public Treatment save(Treatment treatment) throws EntityValidationException {

        treatmentValidator.validate(treatment);
        return treatmentRepository.save(treatment);
    }

    public void delete(Long id) throws EntityNotFoundException {
        Treatment treatment = treatmentRepository.findOne(id);

        if (treatment == null)
            throw new EntityNotFoundException("entity.notFound");

        treatment.setModelStatus(ModelStatus.DELETED);
        treatmentRepository.save(treatment);
    }

    public void update(Long id, Treatment treatment) throws EntityNotFoundException, EntityValidationException {

        Treatment treatmentToUpdate = treatmentRepository.findOne(id);
        if (treatmentToUpdate == null) {
            throw new EntityNotFoundException("entity.notFound");
        }

        treatmentValidator.validate(treatment);

        treatment.setId(treatmentToUpdate.getId());

        treatmentRepository.save(treatment);

    }
}
