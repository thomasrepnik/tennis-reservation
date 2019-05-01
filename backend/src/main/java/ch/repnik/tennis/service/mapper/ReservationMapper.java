package ch.repnik.tennis.service.mapper;

import ch.repnik.tennis.domain.*;
import ch.repnik.tennis.service.dto.ReservationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Reservation and its DTO ReservationDTO.
 */
@Mapper(componentModel = "spring", uses = {ReservationTypeMapper.class})
public interface ReservationMapper extends EntityMapper<ReservationDTO, Reservation> {

    @Mapping(source = "reservationType.id", target = "reservationTypeId")
    ReservationDTO toDto(Reservation reservation);

    @Mapping(source = "reservationTypeId", target = "reservationType")
    Reservation toEntity(ReservationDTO reservationDTO);

    default Reservation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setId(id);
        return reservation;
    }
}
