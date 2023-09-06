package com.worstmovie.api.processor;
import com.worstmovie.api.dto.request.ProducersRequestDTO;
import com.worstmovie.api.service.ProducersService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;

@ApplicationScoped
public class CSVProcessor {

    private static final String[] FILE_HEADER_MAPPING = {"year", "title", "studios", "producers", "winner"};

    @Inject
    ProducersService producersService;

    public void processCSV(FileReader cvsFileReader) {
        try {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(FILE_HEADER_MAPPING)
                    .setDelimiter(';')
                    .setSkipHeaderRecord(true)
                    .build();
            Iterable<CSVRecord> records = csvFormat.parse(cvsFileReader);
            for (CSVRecord record : records) {
                String year = record.get("year");
                String title = record.get("title");
                String studios = record.get("studios");
                String producers = record.get("producers");
                String winner = record.get("winner");
                saveProducers(producers);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveProducers(String nome) {
        producersService.saveProducer(ProducersRequestDTO
                .builder()
                .name(nome)
                .build());
    }
}
