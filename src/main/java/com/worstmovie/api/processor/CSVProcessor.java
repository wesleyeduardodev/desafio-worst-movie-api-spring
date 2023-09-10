package com.worstmovie.api.processor;
import com.worstmovie.api.service.CSVMovieListImporterService;
import com.worstmovie.api.utils.CSVMovieListUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.csv.CSVFormat;
import java.io.FileReader;
import java.io.IOException;

@ApplicationScoped
public class CSVProcessor {

    public static final String CSV_DELIMITER = ";";

    @Inject
    CSVMovieListImporterService csvMovieListImporterService;

    public void processCSV(FileReader cvsFileReader) {
        try {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(CSVMovieListUtils.FILE_HEADER_MAPPING)
                    .setDelimiter(CSV_DELIMITER)
                    .setSkipHeaderRecord(Boolean.TRUE)
                    .build();
            csvMovieListImporterService.csvMovieListImporter(csvFormat.parse(cvsFileReader));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
