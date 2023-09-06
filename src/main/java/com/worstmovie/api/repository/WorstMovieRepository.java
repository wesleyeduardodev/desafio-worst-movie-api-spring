package com.worstmovie.api.repository;
import com.worstmovie.api.model.WorstMovie;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WorstMovieRepository implements PanacheRepository<WorstMovie> {
}
