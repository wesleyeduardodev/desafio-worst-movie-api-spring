package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.ProducerResponseDTO;
import com.worstmovie.api.model.Producer;
import com.worstmovie.api.utils.CSVMovieListUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProducersService {

    public List<Producer> findAllProducers() {
        return Producer.listAll();
    }

    @Transactional
    public void saveProducer(String name) {
        Producer.persist(Producer.builder()
                .name(name)
                .build());
    }

    @Transactional
    public void saveAllProducer(List<Producer> producers) {
        Producer.persist(producers);
    }

    public List<ProducerResponseDTO> producersEntityToProducersResponseDTO(List<Producer> producers) {
        List<ProducerResponseDTO> producersDTOS = new ArrayList<>();
        producers.forEach(producer -> producersDTOS.add(ProducerResponseDTO.builder()
                .id(producer.getId())
                .name(producer.getName())
                .build()));
        return producersDTOS;
    }

    public List<Producer> returnProducersFromCSVRecord(CSVRecord producerRecord) {
        return CSVMovieListUtils.splitAndCleanRecords(producerRecord, CSVMovieListUtils.HEADER_PRODUCERS).stream()
                .map(this::buildProducer)
                .collect(Collectors.toList());
    }

    private Producer buildProducer(String nameProducer) {
        return Producer.builder()
                .name((nameProducer))
                .build();
    }
}
