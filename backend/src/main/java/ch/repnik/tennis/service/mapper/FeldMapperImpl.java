package ch.repnik.tennis.service.mapper;

import ch.repnik.tennis.domain.Feld;
import ch.repnik.tennis.domain.ReservationType;
import ch.repnik.tennis.service.dto.FeldDTO;
import ch.repnik.tennis.service.dto.ReservationTypeDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-04-23T13:04:48+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_73 (Oracle Corporation)"
)
@Component
public class FeldMapperImpl implements FeldMapper {

    @Autowired
    private ReservationTypeMapper reservationTypeMapper;

    @Override
    public Feld toEntity(FeldDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Feld feld = new Feld();

        feld.setId( dto.getId() );
        feld.setName( dto.getName() );
        feld.setReservationTypes( reservationTypeDTOSetToReservationTypeSet( dto.getReservationTypes() ) );

        return feld;
    }

    @Override
    public FeldDTO toDto(Feld entity) {
        if ( entity == null ) {
            return null;
        }

        FeldDTO feldDTO = new FeldDTO();

        feldDTO.setId( entity.getId() );
        feldDTO.setName( entity.getName() );
        feldDTO.setReservationTypes( reservationTypeSetToReservationTypeDTOSet( entity.getReservationTypes() ) );

        return feldDTO;
    }

    @Override
    public List<Feld> toEntity(List<FeldDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Feld> list = new ArrayList<Feld>( dtoList.size() );
        for ( FeldDTO feldDTO : dtoList ) {
            list.add( toEntity( feldDTO ) );
        }

        return list;
    }

    @Override
    public List<FeldDTO> toDto(List<Feld> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<FeldDTO> list = new ArrayList<FeldDTO>( entityList.size() );
        for ( Feld feld : entityList ) {
            list.add( toDto( feld ) );
        }

        return list;
    }

    protected Set<ReservationType> reservationTypeDTOSetToReservationTypeSet(Set<ReservationTypeDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<ReservationType> set1 = new HashSet<ReservationType>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ReservationTypeDTO reservationTypeDTO : set ) {
            set1.add( reservationTypeMapper.toEntity( reservationTypeDTO ) );
        }

        return set1;
    }

    protected Set<ReservationTypeDTO> reservationTypeSetToReservationTypeDTOSet(Set<ReservationType> set) {
        if ( set == null ) {
            return null;
        }

        Set<ReservationTypeDTO> set1 = new HashSet<ReservationTypeDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ReservationType reservationType : set ) {
            set1.add( reservationTypeMapper.toDto( reservationType ) );
        }

        return set1;
    }
}
