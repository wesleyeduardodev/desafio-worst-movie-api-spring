package com.worstmovie.api.repository;
import com.worstmovie.api.model.WorstMovie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorstMovieRepository extends CrudRepository<WorstMovie, Long> {
}
