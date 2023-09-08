package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.ProducerResponseDTO;
import com.worstmovie.api.model.Producer;
import com.worstmovie.api.repository.ProducersRepository;
import com.worstmovie.api.utils.CSVMovieListUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.csv.CSVRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProducersService {

    @Inject
    ProducersRepository producersRepository;

    public List<Producer> findAllProducers() {
        return Producer.listAll();
    }

    public Producer saveProducer(String name) {
        return producersRepository.save(Producer
                .builder()
                .name(name)
                .build());
    }

    public Producer saveOrReturnProducer(String name) {
        Optional<Producer> producer = producersRepository.findByName(name);
        return producer.orElseGet(() -> producersRepository.save(Producer.builder().name(name).build()));
    }

    public List<ProducerResponseDTO> producersEntityToProducersResponseDTO(List<Producer> producers) {
        List<ProducerResponseDTO> producersDTOS = new ArrayList<>();
        producers.forEach(producer -> producersDTOS.add(ProducerResponseDTO.builder()
                .id(producer.getId())
                .name(producer.getName())
                .build()));
        return producersDTOS;
    }

    public ProducerResponseDTO producersEntityToProducerResponse(Producer producer) {
        return ProducerResponseDTO
                .builder()
                .id(producer.getId())
                .name(producer.getName())
                .build();
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
