package ch.repnik.tennis.web.rest;
import ch.repnik.tennis.service.FeldReservationTypeService;
import ch.repnik.tennis.web.rest.errors.BadRequestAlertException;
import ch.repnik.tennis.web.rest.util.HeaderUtil;
import ch.repnik.tennis.web.rest.util.ResponseUtil;
import ch.repnik.tennis.service.dto.FeldReservationTypeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FeldReservationType.
 */
@RestController
@RequestMapping("/api")
public class FeldReservationTypeResource {

    private final Logger log = LoggerFactory.getLogger(FeldReservationTypeResource.class);

    private static final String ENTITY_NAME = "feldReservationType";

    private final FeldReservationTypeService feldReservationTypeService;

    public FeldReservationTypeResource(FeldReservationTypeService feldReservationTypeService) {
        this.feldReservationTypeService = feldReservationTypeService;
    }

    /**
     * POST  /feld-reservation-types : Create a new feldReservationType.
     *
     * @param feldReservationTypeDTO the feldReservationTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new feldReservationTypeDTO, or with status 400 (Bad Request) if the feldReservationType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/feld-reservation-types")
    public ResponseEntity<FeldReservationTypeDTO> createFeldReservationType(@RequestBody FeldReservationTypeDTO feldReservationTypeDTO) throws URISyntaxException {
        log.debug("REST request to save FeldReservationType : {}", feldReservationTypeDTO);
        if (feldReservationTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new feldReservationType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeldReservationTypeDTO result = feldReservationTypeService.save(feldReservationTypeDTO);
        return ResponseEntity.created(new URI("/api/feld-reservation-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /feld-reservation-types : Updates an existing feldReservationType.
     *
     * @param feldReservationTypeDTO the feldReservationTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated feldReservationTypeDTO,
     * or with status 400 (Bad Request) if the feldReservationTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the feldReservationTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/feld-reservation-types")
    public ResponseEntity<FeldReservationTypeDTO> updateFeldReservationType(@RequestBody FeldReservationTypeDTO feldReservationTypeDTO) throws URISyntaxException {
        log.debug("REST request to update FeldReservationType : {}", feldReservationTypeDTO);
        if (feldReservationTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeldReservationTypeDTO result = feldReservationTypeService.save(feldReservationTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, feldReservationTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /feld-reservation-types : get all the feldReservationTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of feldReservationTypes in body
     */
    @GetMapping("/feld-reservation-types")
    public List<FeldReservationTypeDTO> getAllFeldReservationTypes() {
        log.debug("REST request to get all FeldReservationTypes");
        return feldReservationTypeService.findAll();
    }

    /**
     * GET  /feld-reservation-types/:id : get the "id" feldReservationType.
     *
     * @param id the id of the feldReservationTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feldReservationTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/feld-reservation-types/{id}")
    public ResponseEntity<FeldReservationTypeDTO> getFeldReservationType(@PathVariable Long id) {
        log.debug("REST request to get FeldReservationType : {}", id);
        Optional<FeldReservationTypeDTO> feldReservationTypeDTO = feldReservationTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(feldReservationTypeDTO);
    }

    /**
     * DELETE  /feld-reservation-types/:id : delete the "id" feldReservationType.
     *
     * @param id the id of the feldReservationTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/feld-reservation-types/{id}")
    public ResponseEntity<Void> deleteFeldReservationType(@PathVariable Long id) {
        log.debug("REST request to delete FeldReservationType : {}", id);
        feldReservationTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
