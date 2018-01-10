package org.jugistanbul.secondopinion.api.repository;

import org.jugistanbul.secondopinion.api.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {

    List<Case> findByPatientId(Long patientId);
}
