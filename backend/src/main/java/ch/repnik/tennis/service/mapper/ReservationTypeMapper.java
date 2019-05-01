package ch.repnik.tennis.service.mapper;

import ch.repnik.tennis.domain.*;
import ch.repnik.tennis.service.dto.ReservationTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ReservationType and its DTO ReservationTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReservationTypeMapper extends EntityMapper<ReservationTypeDTO, ReservationType> {



    default ReservationType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ReservationType reservationType = new ReservationType();
        reservationType.setId(id);
        return reservationType;
    }
}
