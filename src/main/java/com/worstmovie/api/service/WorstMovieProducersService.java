package com.worstmovie.api.service;
import com.worstmovie.api.model.Producer;
import com.worstmovie.api.model.WorstMovie;
import com.worstmovie.api.model.WorstMovieProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class WorstMovieProducersService {

    @Transactional
    public void saveWorstMovieProducers(Producer producer, WorstMovie worstMovie) {
        WorstMovieProducer.persist(WorstMovieProducer.builder()
                .producer(producer)
                .worstMovie(worstMovie)
                .build());
    }
}
