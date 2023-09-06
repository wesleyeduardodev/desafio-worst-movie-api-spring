package com.worstmovie.api.service;
import com.worstmovie.api.model.Producer;
import com.worstmovie.api.model.Studio;
import com.worstmovie.api.model.WorstMovie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class CSVMovieListImporterService {

    @Inject
    ProducersService producersService;

    @Inject
    StudiosService studiosService;

    @Inject
    WorstMovieService worstMovieService;

    public void csvMovieListImporter(Iterable<CSVRecord> csvMovieListRecords) {
        List<Producer> allProducersForSave = new ArrayList<>();
        List<Studio> allStudiosForSave = new ArrayList<>();
        List<WorstMovie> allWorstMovieForSave = new ArrayList<>();
        for (CSVRecord csvMovieRecord : csvMovieListRecords) {
            List<Producer> producers = producersService.returnProducersFromCSVRecord(csvMovieRecord);
            List<Studio> studios = studiosService.returnStudiosFromCSVRecord(csvMovieRecord);
            WorstMovie worstMovie = worstMovieService.returnWorstMovieFromCSVRecord(csvMovieRecord);
            allProducersForSave.addAll(producers);
            allStudiosForSave.addAll(studios);
            allWorstMovieForSave.add(worstMovie);
        }
        allProducersForSave = returnProducersWithoutRepeatedNames(allProducersForSave);
        allStudiosForSave = returnStudiosWithoutRepeatedNames(allStudiosForSave);
        saveRecords(allProducersForSave, allStudiosForSave, allWorstMovieForSave);
    }

    @Transactional
    void saveRecords(List<Producer> producers, List<Studio> studios, List<WorstMovie> worstMovies) {
        producersService.saveAllProducer(producers);
        studiosService.saveAllStudios(studios);
        worstMovieService.saveAllWorstMovie(worstMovies);
    }

    private List<Producer> returnProducersWithoutRepeatedNames(List<Producer> allProducersForSave) {
        return allProducersForSave.stream()
                .filter(distinctByKey(Producer::getName))
                .collect(Collectors.toList());
    }

    private List<Studio> returnStudiosWithoutRepeatedNames(List<Studio> allStudiosForSave) {
        return allStudiosForSave.stream()
                .filter(distinctByKey(Studio::getName))
                .collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
