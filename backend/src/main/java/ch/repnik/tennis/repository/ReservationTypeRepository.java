package ch.repnik.tennis.repository;

import ch.repnik.tennis.domain.ReservationType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ReservationType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReservationTypeRepository extends JpaRepository<ReservationType, Long> {

}
