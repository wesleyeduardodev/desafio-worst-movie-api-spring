package com.worstmovie.api.service;
import com.worstmovie.api.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class WorstMovieStudioService {

    @Transactional
    public void saveWorstMovieStudio(Studio studio, WorstMovie worstMovie) {
        WorstMovieStudios.persist(WorstMovieStudios.builder()
                .studio(studio)
                .worstMovie(worstMovie)
                .build());
    }
}
