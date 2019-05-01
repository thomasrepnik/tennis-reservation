package ch.repnik.tennis.repository;

import ch.repnik.tennis.domain.Feld;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Feld entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeldRepository extends JpaRepository<Feld, Long> {

    @Query(value = "select distinct feld from Feld feld left join fetch feld.reservationTypes",
        countQuery = "select count(distinct feld) from Feld feld")
    Page<Feld> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct feld from Feld feld left join fetch feld.reservationTypes")
    List<Feld> findAllWithEagerRelationships();

    @Query("select feld from Feld feld left join fetch feld.reservationTypes where feld.id =:id")
    Optional<Feld> findOneWithEagerRelationships(@Param("id") Long id);

}
