package fr.epita.movies.web.rest;

import fr.epita.movies.domain.SeenMovie;
import fr.epita.movies.repository.SeenMovieRepository;
import fr.epita.movies.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.epita.movies.domain.SeenMovie}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SeenMovieResource {

    private final Logger log = LoggerFactory.getLogger(SeenMovieResource.class);

    private static final String ENTITY_NAME = "seenMovie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SeenMovieRepository seenMovieRepository;

    public SeenMovieResource(SeenMovieRepository seenMovieRepository) {
        this.seenMovieRepository = seenMovieRepository;
    }

    /**
     * {@code POST  /seen-movies} : Create a new seenMovie.
     *
     * @param seenMovie the seenMovie to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new seenMovie, or with status {@code 400 (Bad Request)} if the seenMovie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/seen-movies")
    public ResponseEntity<SeenMovie> createSeenMovie(@RequestBody SeenMovie seenMovie) throws URISyntaxException {
        log.debug("REST request to save SeenMovie : {}", seenMovie);
        if (seenMovie.getId() != null) {
            throw new BadRequestAlertException("A new seenMovie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SeenMovie result = seenMovieRepository.save(seenMovie);
        return ResponseEntity.created(new URI("/api/seen-movies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /seen-movies} : Updates an existing seenMovie.
     *
     * @param seenMovie the seenMovie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated seenMovie,
     * or with status {@code 400 (Bad Request)} if the seenMovie is not valid,
     * or with status {@code 500 (Internal Server Error)} if the seenMovie couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/seen-movies")
    public ResponseEntity<SeenMovie> updateSeenMovie(@RequestBody SeenMovie seenMovie) throws URISyntaxException {
        log.debug("REST request to update SeenMovie : {}", seenMovie);
        if (seenMovie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SeenMovie result = seenMovieRepository.save(seenMovie);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, seenMovie.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /seen-movies} : get all the seenMovies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of seenMovies in body.
     */
    @GetMapping("/seen-movies")
    public List<SeenMovie> getAllSeenMovies() {
        log.debug("REST request to get all SeenMovies");
        return seenMovieRepository.findAll();
    }

    /**
     * {@code GET  /seen-movies/:id} : get the "id" seenMovie.
     *
     * @param id the id of the seenMovie to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the seenMovie, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/seen-movies/{id}")
    public ResponseEntity<SeenMovie> getSeenMovie(@PathVariable Long id) {
        log.debug("REST request to get SeenMovie : {}", id);
        Optional<SeenMovie> seenMovie = seenMovieRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(seenMovie);
    }

    /**
     * {@code DELETE  /seen-movies/:id} : delete the "id" seenMovie.
     *
     * @param id the id of the seenMovie to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/seen-movies/{id}")
    public ResponseEntity<Void> deleteSeenMovie(@PathVariable Long id) {
        log.debug("REST request to delete SeenMovie : {}", id);
        seenMovieRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
