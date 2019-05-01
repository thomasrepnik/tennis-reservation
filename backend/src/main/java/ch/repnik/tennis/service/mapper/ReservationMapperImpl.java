package ch.repnik.tennis.service.mapper;

import ch.repnik.tennis.domain.Reservation;
import ch.repnik.tennis.domain.ReservationType;
import ch.repnik.tennis.service.dto.ReservationDTO;
import ch.repnik.tennis.service.dto.UserSlimDTO;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-04-23T13:04:48+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_73 (Oracle Corporation)"
)
@Component
public class ReservationMapperImpl implements ReservationMapper {

	@Autowired
	private UserMapper userMapper;
	
    @Autowired
    private ReservationTypeMapper reservationTypeMapper;
    
    @Autowired
    private FeldMapper feldMapper;

    @Override
    public List<Reservation> toEntity(List<ReservationDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Reservation> list = new ArrayList<Reservation>( dtoList.size() );
        for ( ReservationDTO reservationDTO : dtoList ) {
            list.add( toEntity( reservationDTO ) );
        }

        return list;
    }

    @Override
    public List<ReservationDTO> toDto(List<Reservation> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ReservationDTO> list = new ArrayList<ReservationDTO>( entityList.size() );
        for ( Reservation reservation : entityList ) {
            list.add( toDto( reservation ) );
        }

        return list;
    }

    @Override
    public ReservationDTO toDto(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationDTO reservationDTO = new ReservationDTO();
        
        reservationDTO.setReservationType(reservationTypeMapper.toDto(reservation.getReservationType()));
        reservationDTO.setId( reservation.getId() );
        reservationDTO.setDatum( reservation.getDatum() );
        reservationDTO.setFeld(feldMapper.toDto(reservation.getFeld()));
        reservationDTO.setStart( reservation.getStart() );
        reservationDTO.setEnde( reservation.getEnde() );
        if (reservation.getSpieler1() != null) {
        	reservationDTO.setSpieler1(new UserSlimDTO(reservation.getSpieler1()) );        	
        }
        
        if (reservation.getSpieler2() != null) {
        	reservationDTO.setSpieler2(new UserSlimDTO( reservation.getSpieler2() ));        	
        }
        
        if (reservation.getSpieler3() != null) {
        	reservationDTO.setSpieler3(new UserSlimDTO( reservation.getSpieler3() ));        	
        }
        
        if (reservation.getSpieler4() != null) {
        	reservationDTO.setSpieler4(new UserSlimDTO( reservation.getSpieler4() ));        	
        }
        
        reservationDTO.setGast1( reservation.getGast1() );
        reservationDTO.setGast2( reservation.getGast2() );
        reservationDTO.setGast3( reservation.getGast3() );
        reservationDTO.setGast4( reservation.getGast4() );

        return reservationDTO;
    }

    @Override
    public Reservation toEntity(ReservationDTO reservationDTO) {
        if ( reservationDTO == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setReservationType( reservationTypeMapper.fromId( reservationDTO.getReservationType().getId() ) );
        reservation.setFeld(feldMapper.fromId(reservationDTO.getFeld().getId()));
        reservation.setId( reservationDTO.getId() );
        reservation.setDatum( reservationDTO.getDatum() );
        reservation.setStart( reservationDTO.getStart() );
        reservation.setEnde( reservationDTO.getEnde() );
        
        if (reservationDTO.getSpieler1() != null) {
        	reservation.setSpieler1(userMapper.userFromId(reservationDTO.getSpieler1().getId()));        	
        }else {
        	reservation.setSpieler1(null);
        }
        
        if (reservationDTO.getSpieler2() != null) {
        	reservation.setSpieler2(userMapper.userFromId(reservationDTO.getSpieler2().getId()));
        }else {
        	reservation.setSpieler2(null);
        }
        
        if (reservationDTO.getSpieler3() != null) {
        	reservation.setSpieler3(userMapper.userFromId(reservationDTO.getSpieler3().getId()));
        }else {
        	reservation.setSpieler3(null);
        }
        
        if (reservationDTO.getSpieler4() != null) {
        	reservation.setSpieler4(userMapper.userFromId(reservationDTO.getSpieler4().getId()));
        }else {
        	reservation.setSpieler4(null);
        }
        
        reservation.setGast1( reservationDTO.getGast1() );
        reservation.setGast2( reservationDTO.getGast2() );
        reservation.setGast3( reservationDTO.getGast3() );
        reservation.setGast4( reservationDTO.getGast4() );

        return reservation;
    }

    private Long reservationReservationTypeId(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }
        ReservationType reservationType = reservation.getReservationType();
        if ( reservationType == null ) {
            return null;
        }
        Long id = reservationType.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
