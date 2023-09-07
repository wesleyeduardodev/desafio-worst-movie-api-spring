package com.worstmovie.api.service;
import com.worstmovie.api.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import java.util.List;

@ApplicationScoped
@Slf4j
public class CSVMovieListImporterService {

    @Inject
    ProducersService producersService;

    @Inject
    StudiosService studiosService;

    @Inject
    WorstMovieService worstMovieService;

    @Inject
    WorstMovieProducersService worstMovieProducersService;

    @Inject
    WorstMovieStudioService worstMovieStudioService;

    public void csvMovieListImporter(Iterable<CSVRecord> csvMovieListRecords) {
        for (CSVRecord csvMovieRecord : csvMovieListRecords) {
            WorstMovie worstMovie = worstMovieService.returnWorstMovieFromCSVRecord(csvMovieRecord);
            List<Producer> producers = producersService.returnProducersFromCSVRecord(csvMovieRecord);
            List<Studio> studios = studiosService.returnStudiosFromCSVRecord(csvMovieRecord);
            saveRecords(producers, studios, worstMovie);
        }
    }

    private void saveRecords(List<Producer> producers, List<Studio> studios, WorstMovie worstMovie) {
        WorstMovie worstMovieSaved = worstMovieService.saveWorstMovie(worstMovie);
        saveProducers(producers, worstMovieSaved);
        saveStudios(studios, worstMovieSaved);
    }

    private void saveProducers(List<Producer> producers, WorstMovie worstMovie) {
        producers.forEach(producer -> {
            Producer producerSaved = producersService.saveOrReturnProducer(producer.getName());
            worstMovieProducersService.saveWorstMovieProducers(producerSaved, worstMovie);
        });
    }

    private void saveStudios(List<Studio> studios, WorstMovie worstMovie) {
        studios.forEach(studio -> {
            Studio studioSaved = studiosService.saveOrReturnStudio(studio.getName());
            worstMovieStudioService.saveWorstMovieStudio(studioSaved, worstMovie);
        });
    }
}
