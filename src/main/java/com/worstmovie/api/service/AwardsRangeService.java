package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.MaxMinAwardsRangeDTO;
import com.worstmovie.api.dto.response.WorstMovieProducerDTO;
import com.worstmovie.api.repository.AwardsRangeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class AwardsRangeService {

    @Inject
    AwardsRangeRepository awardsRangeRepository;

    public MaxMinAwardsRangeDTO findAwardsRangeProducer() {
        List<WorstMovieProducerDTO> worstMovieProducerDTOS = awardsRangeRepository.findWorstMovieProducerDTO();
        return MaxMinAwardsRangeDTO.builder().build();
    }
}
