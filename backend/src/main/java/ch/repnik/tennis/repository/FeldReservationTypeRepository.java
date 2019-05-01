package ch.repnik.tennis.repository;

import ch.repnik.tennis.domain.FeldReservationType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FeldReservationType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeldReservationTypeRepository extends JpaRepository<FeldReservationType, Long> {

}
