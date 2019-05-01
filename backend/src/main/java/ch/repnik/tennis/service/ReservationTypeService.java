package ch.repnik.tennis.service;

import ch.repnik.tennis.domain.ReservationType;
import ch.repnik.tennis.repository.ReservationTypeRepository;
import ch.repnik.tennis.service.dto.ReservationTypeDTO;
import ch.repnik.tennis.service.mapper.ReservationTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ReservationType.
 */
@Service
@Transactional
public class ReservationTypeService {

    private final Logger log = LoggerFactory.getLogger(ReservationTypeService.class);

    private final ReservationTypeRepository reservationTypeRepository;

    private final ReservationTypeMapper reservationTypeMapper;

    public ReservationTypeService(ReservationTypeRepository reservationTypeRepository, ReservationTypeMapper reservationTypeMapper) {
        this.reservationTypeRepository = reservationTypeRepository;
        this.reservationTypeMapper = reservationTypeMapper;
    }

    /**
     * Save a reservationType.
     *
     * @param reservationTypeDTO the entity to save
     * @return the persisted entity
     */
    public ReservationTypeDTO save(ReservationTypeDTO reservationTypeDTO) {
        log.debug("Request to save ReservationType : {}", reservationTypeDTO);
        ReservationType reservationType = reservationTypeMapper.toEntity(reservationTypeDTO);
        reservationType = reservationTypeRepository.save(reservationType);
        return reservationTypeMapper.toDto(reservationType);
    }

    /**
     * Get all the reservationTypes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ReservationTypeDTO> findAll() {
        log.debug("Request to get all ReservationTypes");
        return reservationTypeRepository.findAll().stream()
            .map(reservationTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one reservationType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ReservationTypeDTO> findOne(Long id) {
        log.debug("Request to get ReservationType : {}", id);
        return reservationTypeRepository.findById(id)
            .map(reservationTypeMapper::toDto);
    }

    /**
     * Delete the reservationType by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ReservationType : {}", id);
        reservationTypeRepository.deleteById(id);
    }
}
