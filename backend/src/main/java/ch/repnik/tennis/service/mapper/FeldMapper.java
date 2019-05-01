package ch.repnik.tennis.service.mapper;

import ch.repnik.tennis.domain.*;
import ch.repnik.tennis.service.dto.FeldDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Feld and its DTO FeldDTO.
 */
@Mapper(componentModel = "spring", uses = {ReservationTypeMapper.class})
public interface FeldMapper extends EntityMapper<FeldDTO, Feld> {



    default Feld fromId(Long id) {
        if (id == null) {
            return null;
        }
        Feld feld = new Feld();
        feld.setId(id);
        return feld;
    }
}
