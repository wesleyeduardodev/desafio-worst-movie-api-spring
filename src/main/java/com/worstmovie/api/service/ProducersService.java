package com.worstmovie.api.service;
import com.worstmovie.api.dto.request.ProducersRequestDTO;
import com.worstmovie.api.dto.response.ProducersResponseDTO;
import com.worstmovie.api.model.Producers;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProducersService {

    public List<Producers> findAllProducers() {
        return Producers.listAll();
    }

    @Transactional
    public void saveProducer(ProducersRequestDTO producersRequestDTO) {
        Producers.persist(Producers.builder()
                .name(producersRequestDTO.getName())
                .build());
    }

    public List<ProducersResponseDTO> producersEntityToProducersResponseDTO(List<Producers> producers) {
        List<ProducersResponseDTO> producersDTOS = new ArrayList<>();
        producers.forEach(producer -> producersDTOS.add(ProducersResponseDTO.builder()
                .id(producer.getId())
                .name(producer.getName())
                .build()));
        return producersDTOS;
    }
}
