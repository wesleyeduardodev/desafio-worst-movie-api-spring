package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.ProducerResponseDTO;
import com.worstmovie.api.dto.request.ProducerRequestDTO;
import com.worstmovie.api.model.Producer;
import com.worstmovie.api.repository.ProducersRepository;
import com.worstmovie.api.utils.CSVMovieListUtils;
import com.worstmovie.api.utils.LinksUtils;
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

    public Producer saveProducer(String name) {
        return producersRepository.save(Producer
                .builder()
                .name(name)
                .build());
    }

    public void deleteProducers(Long id) {
        Producer.deleteById(id);
    }

    public void updateProducers(Long id, ProducerRequestDTO producersRequestDTO) {
        Producer producer = Producer.findById(id);
        producer.setName(producersRequestDTO.getName());
        producersRepository.save(producer);
    }

    public Producer saveOrReturnProducer(String name) {
        Optional<Producer> producer = producersRepository.findByName(name);
        return producer.orElseGet(() -> producersRepository.save(Producer.builder().name(name).build()));
    }

    public List<ProducerResponseDTO> toProducersResponseDTO(List<Producer> producers, String pathRequest) {
        List<ProducerResponseDTO> producersDTOS = new ArrayList<>();
        producers.forEach(producer -> producersDTOS.add(ProducerResponseDTO.builder()
                .id(producer.getId())
                .name(producer.getName())
                .links(LinksUtils.generateLinks(pathRequest, producer.getId()))
                .build()));
        return producersDTOS;
    }

    public ProducerResponseDTO toProducerResponseDTO(Producer producer, String path) {
        return ProducerResponseDTO
                .builder()
                .id(producer.getId())
                .name(producer.getName())
                .links(LinksUtils.generateLinks(path, null))
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
