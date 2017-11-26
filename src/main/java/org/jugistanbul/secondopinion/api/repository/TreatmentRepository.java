package org.jugistanbul.secondopinion.api.repository;

import org.jugistanbul.secondopinion.api.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    @Override
    @Query("from Treatment t left join fetch t.relevantCase where t.id=:id")
    Treatment findOne(@Param("id") Long aLong);
}
