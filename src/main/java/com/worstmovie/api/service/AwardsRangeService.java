package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.AwardsRangeDTO;
import com.worstmovie.api.dto.response.MaxMinAwardsRangeDTO;
import com.worstmovie.api.dto.response.RankingProducerDTO;
import com.worstmovie.api.dto.response.WorstMovieProducerDTO;
import com.worstmovie.api.repository.AwardsRangeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import java.util.*;

@ApplicationScoped
@Slf4j
public class AwardsRangeService {

    @Inject
    AwardsRangeRepository awardsRangeRepository;

    private static final Integer RANGE_DELIMITER_AWARDS = 2;

    public MaxMinAwardsRangeDTO findAwardsRangeProducer() {
        List<WorstMovieProducerDTO> worstMovieProducers = awardsRangeRepository.findWorstMovieProducerDTO();
        List<RankingProducerDTO> rankingWorstMovieProducers = convertWorstMovieProducerDTOToRankingProducerDTO(worstMovieProducers);
        List<RankingProducerDTO> rankingProducersWithCalculatedPremiumRanges = returnRankingProducersWithCalculatedPremiumRanges(rankingWorstMovieProducers);
        List<RankingProducerDTO> minAwardsProducers = new ArrayList<>();
        List<RankingProducerDTO> maxAwardsProducers = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(rankingProducersWithCalculatedPremiumRanges)) {
            minAwardsProducers = returnMinAwardsProduces(rankingProducersWithCalculatedPremiumRanges);
            maxAwardsProducers = returnMaxAwardsProduces(rankingProducersWithCalculatedPremiumRanges);
        }
        return MaxMinAwardsRangeDTO
                .builder()
                .min(mountAwardsRangeDTO(minAwardsProducers))
                .max(mountAwardsRangeDTO(maxAwardsProducers))
                .build();
    }

    private List<RankingProducerDTO> convertWorstMovieProducerDTOToRankingProducerDTO(List<WorstMovieProducerDTO> worstMovieProducers) {
        List<RankingProducerDTO> rankingWorstMovieProducers = new ArrayList<>();
        worstMovieProducers.forEach(worstMovieProducer -> {
            rankingWorstMovieProducers.add(RankingProducerDTO
                    .builder()
                    .name(worstMovieProducer.getName())
                    .years(worstMovieProducer.getYears())
                    .build());
        });
        return rankingWorstMovieProducers;
    }

    private List<RankingProducerDTO> returnMaxAwardsProduces(List<RankingProducerDTO> rankingProducersWithCalculatedPremiumRanges) {
        Integer maxInterval = returnMaxInterval(rankingProducersWithCalculatedPremiumRanges);
        return rankingProducersWithCalculatedPremiumRanges.stream()
                .filter(i -> i.getInterval().equals(maxInterval)).toList();
    }

    private List<RankingProducerDTO> returnMinAwardsProduces(List<RankingProducerDTO> rankingProducersWithCalculatedPremiumRanges) {
        Integer minInterval = returnMinInterval(rankingProducersWithCalculatedPremiumRanges);
        return rankingProducersWithCalculatedPremiumRanges.stream()
                .filter(i -> i.getInterval().equals(minInterval)).toList();
    }

    private List<RankingProducerDTO> returnRankingProducersWithCalculatedPremiumRanges(List<RankingProducerDTO> rankingProducerDTOS) {
        List<RankingProducerDTO> rankingProducersWithCalculatedPremiumRanges = new ArrayList<>();
        rankingProducerDTOS.forEach(rankingProducer -> {
            if (CollectionUtils.size(rankingProducer.getYears()) == RANGE_DELIMITER_AWARDS) {
                rankingProducer.setInterval(returnInterval(rankingProducer.getYears()));
                rankingProducersWithCalculatedPremiumRanges.add(rankingProducer);
            } else if (CollectionUtils.size(rankingProducer.getYears()) > RANGE_DELIMITER_AWARDS) {
                resolveRankingWithMoreThanTwoConsecutiveAward(rankingProducersWithCalculatedPremiumRanges, rankingProducer);
            }
        });
        return rankingProducersWithCalculatedPremiumRanges;
    }

    private void resolveRankingWithMoreThanTwoConsecutiveAward(List<RankingProducerDTO> rankingProducerNormalizados, RankingProducerDTO rankingProducer) {
        Iterator<Integer> years = rankingProducer.getYears().iterator();
        Integer previousWin = null;
        Integer followingWin;
        while (years.hasNext()) {
            followingWin = years.next();
            if (Objects.nonNull(previousWin)) {
                RankingProducerDTO rankingProducerDTO = RankingProducerDTO
                        .builder()
                        .name(rankingProducer.getName())
                        .years(Arrays.asList(previousWin, followingWin))
                        .build();
                rankingProducerDTO.setInterval(returnInterval(rankingProducerDTO.getYears()));
                rankingProducerNormalizados.add(rankingProducerDTO);
            }
            previousWin = followingWin;
        }
    }

    private List<AwardsRangeDTO> mountAwardsRangeDTO(List<RankingProducerDTO> rankingProducer) {
        List<AwardsRangeDTO> minAwardsRange = new ArrayList<>();
        rankingProducer.forEach(minRankingProduce -> minAwardsRange.add(AwardsRangeDTO
                .builder()
                .producer(minRankingProduce.getName())
                .previousWin(minRankingProduce.getYears().get(0))
                .followingWin(minRankingProduce.getYears().get(1))
                .interval(minRankingProduce.getInterval())
                .build()));
        return minAwardsRange;
    }

    private Integer returnMinInterval(List<RankingProducerDTO> rankingProducerNormalizados) {
        return rankingProducerNormalizados.stream()
                .min(Comparator.comparing(RankingProducerDTO::getInterval))
                .get().getInterval();
    }

    private Integer returnMaxInterval(List<RankingProducerDTO> rankingProducerNormalizados) {
        return rankingProducerNormalizados.stream()
                .max(Comparator.comparing(RankingProducerDTO::getInterval))
                .get().getInterval();
    }

    private Integer returnInterval(List<Integer> years) {
        return Math.abs(years.stream().reduce(0, (previousWin, followingWin) -> followingWin - previousWin));
    }
}
