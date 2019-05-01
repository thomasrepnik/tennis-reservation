package ch.repnik.tennis.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.repnik.tennis.service.ReservationService;
import ch.repnik.tennis.service.dto.ReservationDTO;
import ch.repnik.tennis.web.rest.errors.BadRequestAlertException;
import ch.repnik.tennis.web.rest.util.HeaderUtil;
import ch.repnik.tennis.web.rest.util.ResponseUtil;

/**
 * REST controller for managing Reservation.
 */
@RestController
@RequestMapping("/api")
public class ReservationResource {

    private final Logger log = LoggerFactory.getLogger(ReservationResource.class);

    private static final String ENTITY_NAME = "reservation";

    private final ReservationService reservationService;

    public ReservationResource(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * POST  /reservations : Create a new reservation.
     *
     * @param reservationDTO the reservationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reservationDTO, or with status 400 (Bad Request) if the reservation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reservations")
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDTO) throws URISyntaxException {
        log.debug("REST request to save Reservation : {}", reservationDTO);
        if (reservationDTO.getId() != null) {
            throw new BadRequestAlertException("A new reservation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReservationDTO result = reservationService.save(reservationDTO);
        return ResponseEntity.created(new URI("/api/reservations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reservations : Updates an existing reservation.
     *
     * @param reservationDTO the reservationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reservationDTO,
     * or with status 400 (Bad Request) if the reservationDTO is not valid,
     * or with status 500 (Internal Server Error) if the reservationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reservations")
    public ResponseEntity<ReservationDTO> updateReservation(@Valid @RequestBody ReservationDTO reservationDTO) throws URISyntaxException {
        log.debug("REST request to update Reservation : {}", reservationDTO);
        if (reservationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReservationDTO result = reservationService.save(reservationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reservationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reservations : get all the reservations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of reservations in body
     */
    @GetMapping("/reservations2")
    public List<ReservationDTO> getAllReservations() {
        log.debug("REST request to get all Reservations");
        return reservationService.findAll();
    }
    
    @GetMapping("/reservations")
    public List<ReservationDTO> getAllReservationsByDate(@RequestParam String date) {
        log.debug("REST request to get all Reservations by date " + date);
        
      //Create a DateTimeFormatter with your required format:
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //Next parse the date from the @RequestParam, specifying the TO type as a TemporalQuery:
        LocalDate localDate = LocalDate.parse(date, dateTimeFormat);

        
        return reservationService.findAllByDatum(localDate);
    }

    /**
     * GET  /reservations/:id : get the "id" reservation.
     *
     * @param id the id of the reservationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reservationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationDTO> getReservation(@PathVariable Long id) {
        log.debug("REST request to get Reservation : {}", id);
        Optional<ReservationDTO> reservationDTO = reservationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reservationDTO);
    }

    /**
     * DELETE  /reservations/:id : delete the "id" reservation.
     *
     * @param id the id of the reservationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        log.debug("REST request to delete Reservation : {}", id);
        reservationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
