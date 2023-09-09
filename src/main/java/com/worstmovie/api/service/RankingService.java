package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.*;
import com.worstmovie.api.repository.RankingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

@ApplicationScoped
@Slf4j
public class RankingService {

    @Inject
    RankingRepository awardsRangeRepository;

    public List<RankingDTO> findWorstMovieProducer() {
        return awardsRangeRepository.findWorstMovieProducerDTO();
    }

    public List<RankingDTO> findWorstMovieStudio() {
        return awardsRangeRepository.findWorstMovieStudioDTO();
    }
}
