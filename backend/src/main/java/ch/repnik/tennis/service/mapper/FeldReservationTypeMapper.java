package ch.repnik.tennis.service.mapper;

import ch.repnik.tennis.domain.*;
import ch.repnik.tennis.service.dto.FeldReservationTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FeldReservationType and its DTO FeldReservationTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FeldReservationTypeMapper extends EntityMapper<FeldReservationTypeDTO, FeldReservationType> {



    default FeldReservationType fromId(Long id) {
        if (id == null) {
            return null;
        }
        FeldReservationType feldReservationType = new FeldReservationType();
        feldReservationType.setId(id);
        return feldReservationType;
    }
}
