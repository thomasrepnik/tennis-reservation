package ch.repnik.tennis.service;

import ch.repnik.tennis.domain.FeldReservationType;
import ch.repnik.tennis.repository.FeldReservationTypeRepository;
import ch.repnik.tennis.service.dto.FeldReservationTypeDTO;
import ch.repnik.tennis.service.mapper.FeldReservationTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing FeldReservationType.
 */
@Service
@Transactional
public class FeldReservationTypeService {

    private final Logger log = LoggerFactory.getLogger(FeldReservationTypeService.class);

    private final FeldReservationTypeRepository feldReservationTypeRepository;

    private final FeldReservationTypeMapper feldReservationTypeMapper;

    public FeldReservationTypeService(FeldReservationTypeRepository feldReservationTypeRepository, FeldReservationTypeMapper feldReservationTypeMapper) {
        this.feldReservationTypeRepository = feldReservationTypeRepository;
        this.feldReservationTypeMapper = feldReservationTypeMapper;
    }

    /**
     * Save a feldReservationType.
     *
     * @param feldReservationTypeDTO the entity to save
     * @return the persisted entity
     */
    public FeldReservationTypeDTO save(FeldReservationTypeDTO feldReservationTypeDTO) {
        log.debug("Request to save FeldReservationType : {}", feldReservationTypeDTO);
        FeldReservationType feldReservationType = feldReservationTypeMapper.toEntity(feldReservationTypeDTO);
        feldReservationType = feldReservationTypeRepository.save(feldReservationType);
        return feldReservationTypeMapper.toDto(feldReservationType);
    }

    /**
     * Get all the feldReservationTypes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FeldReservationTypeDTO> findAll() {
        log.debug("Request to get all FeldReservationTypes");
        return feldReservationTypeRepository.findAll().stream()
            .map(feldReservationTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one feldReservationType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<FeldReservationTypeDTO> findOne(Long id) {
        log.debug("Request to get FeldReservationType : {}", id);
        return feldReservationTypeRepository.findById(id)
            .map(feldReservationTypeMapper::toDto);
    }

    /**
     * Delete the feldReservationType by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FeldReservationType : {}", id);
        feldReservationTypeRepository.deleteById(id);
    }
}
