package ch.repnik.tennis.service;

import ch.repnik.tennis.domain.Reservation;
import ch.repnik.tennis.repository.ReservationRepository;
import ch.repnik.tennis.service.dto.ReservationDTO;
import ch.repnik.tennis.service.mapper.ReservationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Reservation.
 */
@Service
@Transactional
public class ReservationService {

    private final Logger log = LoggerFactory.getLogger(ReservationService.class);

    private final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    /**
     * Save a reservation.
     *
     * @param reservationDTO the entity to save
     * @return the persisted entity
     */
    public ReservationDTO save(ReservationDTO reservationDTO) {
        log.debug("Request to save Reservation : {}", reservationDTO);
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        reservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(reservation);
    }

    /**
     * Get all the reservations.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ReservationDTO> findAll() {
        log.debug("Request to get all Reservations");
        return reservationRepository.findAll().stream()
            .map(reservationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one reservation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ReservationDTO> findOne(Long id) {
        log.debug("Request to get Reservation : {}", id);
        return reservationRepository.findById(id)
            .map(reservationMapper::toDto);
    }

    /**
     * Delete the reservation by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Reservation : {}", id);
        reservationRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
	public List<ReservationDTO> findAllByDatum(LocalDate date) {
		log.debug("Request to get all Reservations by date " + date);
        return reservationRepository.findAllByDatum(date).stream()
            .map(reservationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
	}
	
    
}
