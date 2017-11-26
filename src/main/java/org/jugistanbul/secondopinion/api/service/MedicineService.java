package org.jugistanbul.secondopinion.api.service;

import org.jugistanbul.secondopinion.api.entity.Medicine;
import org.jugistanbul.secondopinion.api.entity.ModelStatus;
import org.jugistanbul.secondopinion.api.exception.EntityNotFoundException;
import org.jugistanbul.secondopinion.api.exception.EntityValidationException;
import org.jugistanbul.secondopinion.api.repository.MedicineRepository;
import org.jugistanbul.secondopinion.api.service.validator.MedicineValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicineService {

    private MedicineValidator medicineValidator;
    private MedicineRepository medicineRepository;

    public MedicineService(MedicineValidator medicineValidator, MedicineRepository medicineRepository) {
        this.medicineValidator = medicineValidator;
        this.medicineRepository = medicineRepository;
    }

    public Medicine save(Medicine medicine) throws EntityValidationException {

        medicineValidator.validate(medicine);
        return medicineRepository.save(medicine);
    }

    public Medicine getById(Long id) {
        Medicine medicine = medicineRepository.findOne(id);

        if (medicine == null)
            throw new EntityNotFoundException("entity.notFound");

        return medicine;
    }

    public void delete(Long id) throws EntityNotFoundException {
        Medicine medicine = medicineRepository.findOne(id);

        if (medicine == null)
            throw new EntityNotFoundException("entity.notFound");

        medicine.setModelStatus(ModelStatus.DELETED);
        medicineRepository.save(medicine);
    }

    public void update(Long id, Medicine medicine) throws EntityNotFoundException, EntityValidationException {

        Medicine medicineToUpdate = medicineRepository.findOne(id);
        if (medicineToUpdate == null) {
            throw new EntityNotFoundException("entity.notFound");
        }

        medicineValidator.validate(medicine);

        medicine.setId(medicineToUpdate.getId());

        medicineRepository.save(medicine);

    }
}
