package org.jugistanbul.secondopinion.api.repository;

import java.util.List;

import org.jugistanbul.secondopinion.api.entity.Case;
import org.jugistanbul.secondopinion.api.entity.CaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {
	public List<Case> findByStatusNot(CaseStatus caseStatus);
}
