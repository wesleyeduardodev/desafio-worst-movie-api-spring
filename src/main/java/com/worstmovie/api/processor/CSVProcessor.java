package com.worstmovie.api.processor;
import com.worstmovie.api.service.ProducersService;
import com.worstmovie.api.service.StudiosService;
import com.worstmovie.api.service.WorstMovieService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;

@ApplicationScoped
public class CSVProcessor {

    public static String HEADER_YEAR = "year";
    public static String HEADER_TITLE = "title";
    public static String HEADER_STUDIOS = "studios";
    public static String HEADER_PRODUCERS = "producers";
    public static String HEADER_WINNER = "winner";

    private static final String[] FILE_HEADER_MAPPING = {HEADER_YEAR, HEADER_TITLE, HEADER_STUDIOS, HEADER_PRODUCERS, HEADER_WINNER};

    public static String WINNER = "yes";

    @Inject
    ProducersService producersService;

    @Inject
    StudiosService studiosService;

    @Inject
    WorstMovieService worstMovieService;

    public void processCSV(FileReader cvsFileReader) {
        try {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(FILE_HEADER_MAPPING)
                    .setDelimiter(';')
                    .setSkipHeaderRecord(true)
                    .build();
            Iterable<CSVRecord> records = csvFormat.parse(cvsFileReader);
            for (CSVRecord record : records) {
                String yearWorstMovie = record.get(HEADER_YEAR);
                String titleWorstMovie = record.get(HEADER_TITLE);
                String winnerWorstMovie = record.get(HEADER_WINNER);
                String nameStudios = record.get(HEADER_STUDIOS);
                String nameProducers = record.get(HEADER_PRODUCERS);
                saveWorstMovie(Integer.valueOf(yearWorstMovie), titleWorstMovie, winnerWorstMovie.equalsIgnoreCase(WINNER) ? Boolean.TRUE : Boolean.FALSE);
                saveStudio(nameStudios);
                saveProducers(nameProducers);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveStudio(String name) {
        studiosService.saveStudios(name);
    }

    private void saveProducers(String name) {
        producersService.saveProducer(name);
    }

    private void saveWorstMovie(Integer year, String title, boolean winner) {
        worstMovieService.saveWorstMovie(year, title, winner);
    }
}
