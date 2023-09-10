package com.worstmovie.api;
import com.worstmovie.api.processor.CSVProcessor;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.ConfigProvider;
import java.io.FileReader;
import java.io.IOException;

@ApplicationScoped
@Slf4j
public class ApplicationStartupInterceptor {

    private static final String PATH_FILE_CSV = "csv.file.path";

    @Inject
    CSVProcessor csvProcessor;

    void onStart(@Observes StartupEvent ev) {
        log.info("Starting processing of CSV file...");
        try {
            String pathFileCsv = ConfigProvider.getConfig().getConfigValue(PATH_FILE_CSV).getValue();
            if (StringUtils.isBlank(pathFileCsv)) {
                throw new RuntimeException("CSV file path not found in properties.");
            } else {
                csvProcessor.processCSV(new FileReader(pathFileCsv));
            }
        } catch (IOException e) {
            log.error("Error processing CSV file." + e.getMessage());
            throw new RuntimeException(e);
        }
        log.info("CSV file processing completed successfully!");
    }
}
