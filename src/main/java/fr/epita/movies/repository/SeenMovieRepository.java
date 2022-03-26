package fr.epita.movies.repository;

import fr.epita.movies.domain.SeenMovie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SeenMovie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeenMovieRepository extends JpaRepository<SeenMovie, Long> {
}
