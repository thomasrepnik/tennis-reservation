package ch.repnik.tennis.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.repnik.tennis.service.ReservationTypeService;
import ch.repnik.tennis.service.dto.ReservationTypeDTO;
import ch.repnik.tennis.web.rest.errors.BadRequestAlertException;
import ch.repnik.tennis.web.rest.util.HeaderUtil;
import ch.repnik.tennis.web.rest.util.ResponseUtil;

/**
 * REST controller for managing ReservationType.
 */
@RestController
@RequestMapping("/api")
public class ReservationTypeResource {

    private final Logger log = LoggerFactory.getLogger(ReservationTypeResource.class);

    private static final String ENTITY_NAME = "reservationType";

    private final ReservationTypeService reservationTypeService;

    public ReservationTypeResource(ReservationTypeService reservationTypeService) {
        this.reservationTypeService = reservationTypeService;
    }

    /**
     * POST  /reservation-types : Create a new reservationType.
     *
     * @param reservationTypeDTO the reservationTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reservationTypeDTO, or with status 400 (Bad Request) if the reservationType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reservation-types")
    public ResponseEntity<ReservationTypeDTO> createReservationType(@Valid @RequestBody ReservationTypeDTO reservationTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ReservationType : {}", reservationTypeDTO);
        if (reservationTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new reservationType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReservationTypeDTO result = reservationTypeService.save(reservationTypeDTO);
        return ResponseEntity.created(new URI("/api/reservation-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reservation-types : Updates an existing reservationType.
     *
     * @param reservationTypeDTO the reservationTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reservationTypeDTO,
     * or with status 400 (Bad Request) if the reservationTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the reservationTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reservation-types")
    public ResponseEntity<ReservationTypeDTO> updateReservationType(@Valid @RequestBody ReservationTypeDTO reservationTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ReservationType : {}", reservationTypeDTO);
        if (reservationTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReservationTypeDTO result = reservationTypeService.save(reservationTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reservationTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reservation-types : get all the reservationTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of reservationTypes in body
     */
    @GetMapping("/reservation-types")
    public List<ReservationTypeDTO> getAllReservationTypes() {
        log.debug("REST request to get all ReservationTypes");
        return reservationTypeService.findAll();
    }

    /**
     * GET  /reservation-types/:id : get the "id" reservationType.
     *
     * @param id the id of the reservationTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reservationTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/reservation-types/{id}")
    public ResponseEntity<ReservationTypeDTO> getReservationType(@PathVariable Long id) {
        log.debug("REST request to get ReservationType : {}", id);
        Optional<ReservationTypeDTO> reservationTypeDTO = reservationTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reservationTypeDTO);
    }

    /**
     * DELETE  /reservation-types/:id : delete the "id" reservationType.
     *
     * @param id the id of the reservationTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reservation-types/{id}")
    public ResponseEntity<Void> deleteReservationType(@PathVariable Long id) {
        log.debug("REST request to delete ReservationType : {}", id);
        reservationTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
