package ch.repnik.tennis.service;

import ch.repnik.tennis.domain.Feld;
import ch.repnik.tennis.repository.FeldRepository;
import ch.repnik.tennis.service.dto.FeldDTO;
import ch.repnik.tennis.service.mapper.FeldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Feld.
 */
@Service
@Transactional
public class FeldService {

    private final Logger log = LoggerFactory.getLogger(FeldService.class);

    private final FeldRepository feldRepository;

    private final FeldMapper feldMapper;

    public FeldService(FeldRepository feldRepository, FeldMapper feldMapper) {
        this.feldRepository = feldRepository;
        this.feldMapper = feldMapper;
    }

    /**
     * Save a feld.
     *
     * @param feldDTO the entity to save
     * @return the persisted entity
     */
    public FeldDTO save(FeldDTO feldDTO) {
        log.debug("Request to save Feld : {}", feldDTO);
        Feld feld = feldMapper.toEntity(feldDTO);
        feld = feldRepository.save(feld);
        return feldMapper.toDto(feld);
    }

    /**
     * Get all the felds.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FeldDTO> findAll() {
        log.debug("Request to get all Felds");
        return feldRepository.findAllWithEagerRelationships().stream()
            .map(feldMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Feld with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<FeldDTO> findAllWithEagerRelationships(Pageable pageable) {
        return feldRepository.findAllWithEagerRelationships(pageable).map(feldMapper::toDto);
    }
    

    /**
     * Get one feld by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<FeldDTO> findOne(Long id) {
        log.debug("Request to get Feld : {}", id);
        return feldRepository.findOneWithEagerRelationships(id)
            .map(feldMapper::toDto);
    }

    /**
     * Delete the feld by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Feld : {}", id);
        feldRepository.deleteById(id);
    }
}
