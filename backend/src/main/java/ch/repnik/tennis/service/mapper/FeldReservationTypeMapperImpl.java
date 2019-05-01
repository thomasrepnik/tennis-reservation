package ch.repnik.tennis.service.mapper;

import ch.repnik.tennis.domain.FeldReservationType;
import ch.repnik.tennis.service.dto.FeldReservationTypeDTO;
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
public class FeldReservationTypeMapperImpl implements FeldReservationTypeMapper {

    @Override
    public FeldReservationType toEntity(FeldReservationTypeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FeldReservationType feldReservationType = new FeldReservationType();

        feldReservationType.setId( dto.getId() );

        return feldReservationType;
    }

    @Override
    public FeldReservationTypeDTO toDto(FeldReservationType entity) {
        if ( entity == null ) {
            return null;
        }

        FeldReservationTypeDTO feldReservationTypeDTO = new FeldReservationTypeDTO();

        feldReservationTypeDTO.setId( entity.getId() );

        return feldReservationTypeDTO;
    }

    @Override
    public List<FeldReservationType> toEntity(List<FeldReservationTypeDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<FeldReservationType> list = new ArrayList<FeldReservationType>( dtoList.size() );
        for ( FeldReservationTypeDTO feldReservationTypeDTO : dtoList ) {
            list.add( toEntity( feldReservationTypeDTO ) );
        }

        return list;
    }

    @Override
    public List<FeldReservationTypeDTO> toDto(List<FeldReservationType> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<FeldReservationTypeDTO> list = new ArrayList<FeldReservationTypeDTO>( entityList.size() );
        for ( FeldReservationType feldReservationType : entityList ) {
            list.add( toDto( feldReservationType ) );
        }

        return list;
    }
}
