package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.*;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class AwardsRangeService {

    private static final Integer RANGE_DELIMITER_AWARDS = 2;

    public MaxMinProducersAwardsRangeResponseDTO findAwardsRangeProducer(List<RankingDTO> rankingProducers) {
        List<RankingDTO> rankingProducersWithCalculatedPremiumRanges = returnRankingWithCalculatedPremiumRanges(rankingProducers);
        List<RankingDTO> minAwardsProducers = returnMinAwards(rankingProducersWithCalculatedPremiumRanges);
        List<RankingDTO> maxAwardsProducers = returnMaxAwards(rankingProducersWithCalculatedPremiumRanges);
        return MaxMinProducersAwardsRangeResponseDTO
                .builder()
                .min(mountProducerAwardsRangeDTO(minAwardsProducers))
                .max(mountProducerAwardsRangeDTO(maxAwardsProducers))
                .build();
    }

    public MaxMinStudiosAwardsRangeResponseDTO findAwardsRangeStudio(List<RankingDTO> rankingStudios) {
        List<RankingDTO> rankingStudiosWithCalculatedPremiumRanges = returnRankingWithCalculatedPremiumRanges(rankingStudios);
        List<RankingDTO> minAwardsStudios = returnMinAwards(rankingStudiosWithCalculatedPremiumRanges);
        List<RankingDTO> maxAwardsStudios = returnMaxAwards(rankingStudiosWithCalculatedPremiumRanges);
        return MaxMinStudiosAwardsRangeResponseDTO
                .builder()
                .min(mountStudioAwardsRangeDTO(minAwardsStudios))
                .max(mountStudioAwardsRangeDTO(maxAwardsStudios))
                .build();
    }

    private List<RankingDTO> returnMaxAwards(List<RankingDTO> rankingWithCalculatedPremiumRanges) {
        if (CollectionUtils.isNotEmpty(rankingWithCalculatedPremiumRanges)) {
            Integer maxInterval = returnMaxInterval(rankingWithCalculatedPremiumRanges);
            return rankingWithCalculatedPremiumRanges.stream()
                    .filter(i -> i.getInterval().equals(maxInterval)).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    private List<RankingDTO> returnMinAwards(List<RankingDTO> rankingWithCalculatedPremiumRanges) {
        if (CollectionUtils.isNotEmpty(rankingWithCalculatedPremiumRanges)) {
            Integer minInterval = returnMinInterval(rankingWithCalculatedPremiumRanges);
            return rankingWithCalculatedPremiumRanges.stream()
                    .filter(i -> i.getInterval().equals(minInterval)).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    private List<RankingDTO> returnRankingWithCalculatedPremiumRanges(List<RankingDTO> rankingDTOS) {
        List<RankingDTO> rankingWithCalculatedPremiumRanges = new ArrayList<>();
        rankingDTOS.forEach(ranking -> {
            if (CollectionUtils.size(ranking.getYears()) == RANGE_DELIMITER_AWARDS) {
                ranking.setInterval(returnInterval(ranking.getYears()));
                rankingWithCalculatedPremiumRanges.add(ranking);
            } else if (CollectionUtils.size(ranking.getYears()) > RANGE_DELIMITER_AWARDS) {
                resolveRankingWithMoreThanTwoConsecutiveAward(rankingWithCalculatedPremiumRanges, ranking);
            }
        });
        return rankingWithCalculatedPremiumRanges;
    }

    private void resolveRankingWithMoreThanTwoConsecutiveAward(List<RankingDTO> rankings, RankingDTO ranking) {
        Iterator<Integer> years = ranking.getYears().iterator();
        Integer previousWin = null;
        Integer followingWin;
        while (years.hasNext()) {
            followingWin = years.next();
            if (Objects.nonNull(previousWin)) {
                RankingDTO rankingDTO = RankingDTO
                        .builder()
                        .name(ranking.getName())
                        .years(Arrays.asList(previousWin, followingWin))
                        .build();
                rankingDTO.setInterval(returnInterval(rankingDTO.getYears()));
                rankings.add(rankingDTO);
            }
            previousWin = followingWin;
        }
    }

    private List<ProducerAwardsRangeDTO> mountProducerAwardsRangeDTO(List<RankingDTO> rankingProducer) {
        List<ProducerAwardsRangeDTO> minAwardsRange = new ArrayList<>();
        rankingProducer.forEach(minRankingProduce -> minAwardsRange.add(ProducerAwardsRangeDTO
                .builder()
                .producer(minRankingProduce.getName())
                .previousWin(minRankingProduce.getYears().get(0))
                .followingWin(minRankingProduce.getYears().get(1))
                .interval(minRankingProduce.getInterval())
                .build()));
        return minAwardsRange;
    }

    private List<StudioAwardsRangeDTO> mountStudioAwardsRangeDTO(List<RankingDTO> rankingStudio) {
        List<StudioAwardsRangeDTO> minAwardsRange = new ArrayList<>();
        rankingStudio.forEach(minRankingProduce -> minAwardsRange.add(StudioAwardsRangeDTO
                .builder()
                .studio(minRankingProduce.getName())
                .previousWin(minRankingProduce.getYears().get(0))
                .followingWin(minRankingProduce.getYears().get(1))
                .interval(minRankingProduce.getInterval())
                .build()));
        return minAwardsRange;
    }

    private Integer returnMinInterval(List<RankingDTO> rankings) {
        return rankings.stream()
                .min(Comparator.comparing(RankingDTO::getInterval))
                .get().getInterval();
    }

    private Integer returnMaxInterval(List<RankingDTO> rankings) {
        return rankings.stream()
                .max(Comparator.comparing(RankingDTO::getInterval))
                .get().getInterval();
    }

    private Integer returnInterval(List<Integer> years) {
        return Math.abs(years.stream().reduce(0, (previousWin, followingWin) -> followingWin - previousWin));
    }
}
