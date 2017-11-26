package org.jugistanbul.secondopinion.api;

import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestEntityHelper {

    @Autowired
    private CaseRepository caseRepository;


    public Case createTestMedicalCase() {
        Case theCase = new Case();
        theCase.setIllnessStartDate(LocalDate.MAX);
        theCase.setNickname("Lorem");
        theCase.setNote("Lorem ipsum dolor sit amet");
        theCase.setSymptoms("Lorem...");

        return caseRepository.save(theCase);
    }
}
