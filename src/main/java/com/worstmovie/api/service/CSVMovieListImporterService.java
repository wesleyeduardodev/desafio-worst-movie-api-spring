package com.worstmovie.api.service;
import com.worstmovie.api.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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

    private HashMap<String, Producer> mapSavedProducers;
    private HashMap<String, Studio> mapSavedStudios;

    public void csvMovieListImporter(Iterable<CSVRecord> csvMovieListRecords) {
        mapSavedProducers = new HashMap<>();
        mapSavedStudios = new HashMap<>();
        for (CSVRecord csvMovieRecord : csvMovieListRecords) {
            WorstMovie worstMovie = worstMovieService.returnWorstMovieFromCSVRecord(csvMovieRecord);
            List<Producer> producers = producersService.returnProducersFromCSVRecord(csvMovieRecord);
            List<Studio> studios = studiosService.returnStudiosFromCSVRecord(csvMovieRecord);
            saveRecords(producers, studios, worstMovie);
        }
    }

    private void saveRecords(List<Producer> producers, List<Studio> studios, WorstMovie worstMovie) {
        WorstMovie worstMovieSaved = saveWorstMovie(worstMovie);
        List<Producer> producersSaved = saveProducers(producers);
        List<Studio> studiosSaved = saveStudios(studios);
        producersSaved.forEach(producer -> worstMovieProducersService.saveWorstMovieProducers(producer, worstMovieSaved));
        studiosSaved.forEach(sudio -> worstMovieStudioService.saveWorstMovieStudio(sudio, worstMovieSaved));
    }

    private WorstMovie saveWorstMovie(WorstMovie worstMovieRecord) {
        return worstMovieService.saveWorstMovie(WorstMovie
                .builder()
                .year(worstMovieRecord.getYear())
                .title(worstMovieRecord.getTitle())
                .winner(worstMovieRecord.isWinner())
                .build());
    }

    private List<Producer> saveProducers(List<Producer> producersRecord) {
        List<Producer> savedProducers = new ArrayList<>();
        producersRecord.forEach(producerRercord -> {
            Producer producer = mapSavedProducers.get(producerRercord.getName());
            if (Objects.isNull(producer)) {
                producer = Producer
                        .builder()
                        .name(producerRercord.getName())
                        .build();
                producersService.saveProducer(producer);
                mapSavedProducers.put(producer.getName(), producer);
            }
            savedProducers.add(producer);
        });
        return savedProducers;
    }


    private List<Studio> saveStudios(List<Studio> studiosRecord) {
        List<Studio> savedStudios = new ArrayList<>();
        studiosRecord.forEach(studioRecord -> {
            Studio studio = mapSavedStudios.get(studioRecord.getName());
            if (Objects.isNull(studio)) {
                studio = Studio
                        .builder()
                        .name(studioRecord.getName())
                        .build();
                studiosService.saveStudio(studio);
                mapSavedStudios.put(studio.getName(), studio);
            }
            savedStudios.add(studio);
        });
        return savedStudios;
    }
}
