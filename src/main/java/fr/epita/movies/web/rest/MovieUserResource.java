package fr.epita.movies.web.rest;

import fr.epita.movies.domain.MovieUser;
import fr.epita.movies.repository.MovieUserRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link fr.epita.movies.domain.MovieUser}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MovieUserResource {

    private final Logger log = LoggerFactory.getLogger(MovieUserResource.class);

    private static final String ENTITY_NAME = "movieUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MovieUserRepository movieUserRepository;

    public MovieUserResource(MovieUserRepository movieUserRepository) {
        this.movieUserRepository = movieUserRepository;
    }

    /**
     * {@code POST  /movie-users} : Create a new movieUser.
     *
     * @param movieUser the movieUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new movieUser, or with status {@code 400 (Bad Request)} if the movieUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/movie-users")
    public ResponseEntity<MovieUser> createMovieUser(@RequestBody MovieUser movieUser) throws URISyntaxException {
        log.debug("REST request to save MovieUser : {}", movieUser);
        if (movieUser.getId() != null) {
            throw new BadRequestAlertException("A new movieUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovieUser result = movieUserRepository.save(movieUser);
        return ResponseEntity.created(new URI("/api/movie-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /movie-users} : Updates an existing movieUser.
     *
     * @param movieUser the movieUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated movieUser,
     * or with status {@code 400 (Bad Request)} if the movieUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the movieUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/movie-users")
    public ResponseEntity<MovieUser> updateMovieUser(@RequestBody MovieUser movieUser) throws URISyntaxException {
        log.debug("REST request to update MovieUser : {}", movieUser);
        if (movieUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MovieUser result = movieUserRepository.save(movieUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, movieUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /movie-users} : get all the movieUsers.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of movieUsers in body.
     */
    @GetMapping("/movie-users")
    public List<MovieUser> getAllMovieUsers(@RequestParam(required = false) String filter,@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        if ("contact-is-null".equals(filter)) {
            log.debug("REST request to get all MovieUsers where contact is null");
            return StreamSupport
                .stream(movieUserRepository.findAll().spliterator(), false)
                .filter(movieUser -> movieUser.getContact() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all MovieUsers");
        return movieUserRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /movie-users/:id} : get the "id" movieUser.
     *
     * @param id the id of the movieUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the movieUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/movie-users/{id}")
    public ResponseEntity<MovieUser> getMovieUser(@PathVariable Long id) {
        log.debug("REST request to get MovieUser : {}", id);
        Optional<MovieUser> movieUser = movieUserRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(movieUser);
    }

    /**
     * {@code DELETE  /movie-users/:id} : delete the "id" movieUser.
     *
     * @param id the id of the movieUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/movie-users/{id}")
    public ResponseEntity<Void> deleteMovieUser(@PathVariable Long id) {
        log.debug("REST request to delete MovieUser : {}", id);
        movieUserRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
