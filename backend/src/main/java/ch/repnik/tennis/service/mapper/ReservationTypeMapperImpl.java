package ch.repnik.tennis.service.mapper;

import ch.repnik.tennis.domain.ReservationType;
import ch.repnik.tennis.service.dto.ReservationTypeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-04-23T13:04:48+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_73 (Oracle Corporation)"
)
@Component
public class ReservationTypeMapperImpl implements ReservationTypeMapper {

    @Override
    public ReservationType toEntity(ReservationTypeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ReservationType reservationType = new ReservationType();

        reservationType.setId( dto.getId() );
        reservationType.setName( dto.getName() );
        reservationType.setAnzahlspieler( dto.getAnzahlspieler() );
        reservationType.setKontingent( dto.getKontingent() );

        return reservationType;
    }

    @Override
    public ReservationTypeDTO toDto(ReservationType entity) {
        if ( entity == null ) {
            return null;
        }

        ReservationTypeDTO reservationTypeDTO = new ReservationTypeDTO();

        reservationTypeDTO.setId( entity.getId() );
        reservationTypeDTO.setName( entity.getName() );
        reservationTypeDTO.setAnzahlspieler( entity.getAnzahlspieler() );
        reservationTypeDTO.setKontingent( entity.getKontingent() );

        return reservationTypeDTO;
    }

    @Override
    public List<ReservationType> toEntity(List<ReservationTypeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ReservationType> list = new ArrayList<ReservationType>( dtoList.size() );
        for ( ReservationTypeDTO reservationTypeDTO : dtoList ) {
            list.add( toEntity( reservationTypeDTO ) );
        }

        return list;
    }

    @Override
    public List<ReservationTypeDTO> toDto(List<ReservationType> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ReservationTypeDTO> list = new ArrayList<ReservationTypeDTO>( entityList.size() );
        for ( ReservationType reservationType : entityList ) {
            list.add( toDto( reservationType ) );
        }

        return list;
    }
}
