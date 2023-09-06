package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.ProducerResponseDTO;
import com.worstmovie.api.model.Producer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    public List<ProducerResponseDTO> producersEntityToProducersResponseDTO(List<Producer> producers) {
        List<ProducerResponseDTO> producersDTOS = new ArrayList<>();
        producers.forEach(producer -> producersDTOS.add(ProducerResponseDTO.builder()
                .id(producer.getId())
                .name(producer.getName())
                .build()));
        return producersDTOS;
    }
}
