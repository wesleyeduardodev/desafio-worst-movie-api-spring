package com.worstmovie.api;
import com.worstmovie.api.processor.CSVProcessor;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import java.io.FileReader;
import java.io.IOException;

@ApplicationScoped
@Slf4j
public class AppLifecycleBean {

    private static final String PATH_CSV = "src/main/resources/movielist.csv";

    @Inject
    CSVProcessor csvProcessor;

    void onStart(@Observes StartupEvent ev) {
        log.info("Starting processing of CSV file...");
        try {
            csvProcessor.processCSV(new FileReader(PATH_CSV));
        } catch (IOException e) {
            log.error("Error processing CSV file.", e);
            throw new RuntimeException(e);
        }
        log.info("CSV file processing completed successfully!");
    }
}
