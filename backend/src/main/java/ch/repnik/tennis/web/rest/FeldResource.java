package ch.repnik.tennis.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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

import ch.repnik.tennis.service.FeldService;
import ch.repnik.tennis.service.dto.FeldDTO;
import ch.repnik.tennis.web.rest.errors.BadRequestAlertException;
import ch.repnik.tennis.web.rest.util.HeaderUtil;
import ch.repnik.tennis.web.rest.util.ResponseUtil;

/**
 * REST controller for managing Feld.
 */
@RestController
@RequestMapping("/api")
public class FeldResource {

    private final Logger log = LoggerFactory.getLogger(FeldResource.class);

    private static final String ENTITY_NAME = "feld";

    private final FeldService feldService;

    public FeldResource(FeldService feldService) {
        this.feldService = feldService;
    }

    /**
     * POST  /felds : Create a new feld.
     *
     * @param feldDTO the feldDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new feldDTO, or with status 400 (Bad Request) if the feld has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/felds")
    public ResponseEntity<FeldDTO> createFeld(@RequestBody FeldDTO feldDTO) throws URISyntaxException {
        log.debug("REST request to save Feld : {}", feldDTO);
        if (feldDTO.getId() != null) {
            throw new BadRequestAlertException("A new feld cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeldDTO result = feldService.save(feldDTO);
        return ResponseEntity.created(new URI("/api/felds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /felds : Updates an existing feld.
     *
     * @param feldDTO the feldDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated feldDTO,
     * or with status 400 (Bad Request) if the feldDTO is not valid,
     * or with status 500 (Internal Server Error) if the feldDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/felds")
    public ResponseEntity<FeldDTO> updateFeld(@RequestBody FeldDTO feldDTO) throws URISyntaxException {
        log.debug("REST request to update Feld : {}", feldDTO);
        if (feldDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeldDTO result = feldService.save(feldDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, feldDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /felds : get all the felds.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of felds in body
     */
    @GetMapping("/felds")
    public List<FeldDTO> getAllFelds(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Felds");
        return feldService.findAll();
    }

    /**
     * GET  /felds/:id : get the "id" feld.
     *
     * @param id the id of the feldDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feldDTO, or with status 404 (Not Found)
     */
    @GetMapping("/felds/{id}")
    public ResponseEntity<FeldDTO> getFeld(@PathVariable Long id) {
        log.debug("REST request to get Feld : {}", id);
        Optional<FeldDTO> feldDTO = feldService.findOne(id);
        return ResponseUtil.wrapOrNotFound(feldDTO);
    }

    /**
     * DELETE  /felds/:id : delete the "id" feld.
     *
     * @param id the id of the feldDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/felds/{id}")
    public ResponseEntity<Void> deleteFeld(@PathVariable Long id) {
        log.debug("REST request to delete Feld : {}", id);
        feldService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
