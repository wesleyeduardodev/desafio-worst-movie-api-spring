package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.*;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class AwardsRangeServiceTest {

    @Inject
    AwardsRangeService awardsRangeService;

    private static MaxMinProducersAwardsRangeResponseDTO maxMinProducersAwardsRangeMock;
    private static MaxMinStudiosAwardsRangeResponseDTO maxMinStudiosAwardsRangeMock;

    @BeforeAll
    public static void setup() {
        maxMinProducersAwardsRangeMock = getMockMaxMinProducersAwardsRange();
        maxMinStudiosAwardsRangeMock = getMockMaxMinStudiosAwardsRange();
    }

    @Test
    public void testRangeProducerFromMockRankingProducers() {
        List<RankingDTO> rankingDTOS = getRankingProducers();
        MaxMinProducersAwardsRangeResponseDTO maxMinProducersAwardsRange = awardsRangeService.findAwardsRangeProducer(rankingDTOS);
        assertEquals(maxMinProducersAwardsRange.getMin(), maxMinProducersAwardsRangeMock.getMin());
        assertEquals(maxMinProducersAwardsRange.getMax(), maxMinProducersAwardsRangeMock.getMax());
    }

    @Test
    public void testRangeStudiosFromMockRankingProducers() {
        List<RankingDTO> rankingDTOS = getRankingStudios();
        MaxMinStudiosAwardsRangeResponseDTO maxMinStudiosAwardsRangeResponse = awardsRangeService.findAwardsRangeStudio(rankingDTOS);
        assertEquals(maxMinStudiosAwardsRangeResponse.getMin(), maxMinStudiosAwardsRangeMock.getMin());
        assertEquals(maxMinStudiosAwardsRangeResponse.getMax().size(), maxMinStudiosAwardsRangeMock.getMax().size());
    }

    private static MaxMinProducersAwardsRangeResponseDTO getMockMaxMinProducersAwardsRange() {
        ProducerAwardsRangeDTO minRangeProducerWesley = buildProducer("Wesley", 2, 1990, 1992);
        ProducerAwardsRangeDTO minRangeProducerEduardo = buildProducer("Eduardo", 2, 2005, 2007);
        ProducerAwardsRangeDTO maxRangeProducerAlan = buildProducer("Alan Silva", 20, 2050, 2070);
        ProducerAwardsRangeDTO maxRangeProducerCarlos = buildProducer("Carlos Silva", 20, 2000, 2020);
        return MaxMinProducersAwardsRangeResponseDTO
                .builder()
                .min(Arrays.asList(minRangeProducerWesley, minRangeProducerEduardo))
                .max(Arrays.asList(maxRangeProducerAlan, maxRangeProducerCarlos))
                .build();
    }

    private static MaxMinStudiosAwardsRangeResponseDTO getMockMaxMinStudiosAwardsRange() {
        StudioAwardsRangeDTO minRangeStudioWarnerBros = buildSudio("Warner Bros", 1, 1990, 1991);
        StudioAwardsRangeDTO minRangeStudioNetFlix = buildSudio("Netflix", 1, 2000, 2001);
        StudioAwardsRangeDTO maxRangeStudioParamount = buildSudio("Paramount", 20, 2010, 2030);
        StudioAwardsRangeDTO maxRangeStudioWaltDisney = buildSudio(" Walt Disney ", 20, 2020, 2040);
        return MaxMinStudiosAwardsRangeResponseDTO
                .builder()
                .min(Arrays.asList(minRangeStudioWarnerBros, minRangeStudioNetFlix))
                .max(Arrays.asList(maxRangeStudioParamount, maxRangeStudioWaltDisney))
                .build();
    }

    private static ProducerAwardsRangeDTO buildProducer(String producer, Integer interval, Integer previousWin, Integer followingWin) {
        return ProducerAwardsRangeDTO
                .builder()
                .producer(producer)
                .interval(interval)
                .previousWin(previousWin)
                .followingWin(followingWin)
                .build();
    }

    private static StudioAwardsRangeDTO buildSudio(String studio, Integer interval, Integer previousWin, Integer followingWin) {
        return StudioAwardsRangeDTO
                .builder()
                .studio(studio)
                .interval(interval)
                .previousWin(previousWin)
                .followingWin(followingWin)
                .build();
    }

    private List<RankingDTO> getRankingProducers() {
        List<RankingDTO> rankingProducers = new ArrayList<>();
        RankingDTO ranking01 = RankingDTO
                .builder()
                .name("Alan Silva")
                .years(Arrays.asList(2050, 2070))
                .build();
        RankingDTO ranking02 = RankingDTO
                .builder()
                .name("Wesley")
                .years(Arrays.asList(1990, 1992))
                .build();
        RankingDTO ranking03 = RankingDTO
                .builder()
                .name("Carlos Silva")
                .years(Arrays.asList(2000, 2020))
                .build();
        RankingDTO ranking04 = RankingDTO
                .builder()
                .name("Eduardo")
                .years(Arrays.asList(2005, 2007))
                .build();
        RankingDTO ranking05 = RankingDTO
                .builder()
                .name("Gabriel")
                .years(Arrays.asList(2002, 2005))
                .build();
        rankingProducers.add(ranking01);
        rankingProducers.add(ranking02);
        rankingProducers.add(ranking03);
        rankingProducers.add(ranking04);
        rankingProducers.add(ranking05);
        return rankingProducers;
    }

    private List<RankingDTO> getRankingStudios() {
        List<RankingDTO> rankingStudios = new ArrayList<>();
        RankingDTO ranking01 = RankingDTO
                .builder()
                .name("Paramount")
                .years(Arrays.asList(2010, 2030))
                .build();
        RankingDTO ranking02 = RankingDTO
                .builder()
                .name("Warner Bros")
                .years(Arrays.asList(1990, 1991))
                .build();
        RankingDTO ranking03 = RankingDTO
                .builder()
                .name("Walt Disney")
                .years(Arrays.asList(2020, 2040))
                .build();
        RankingDTO ranking04 = RankingDTO
                .builder()
                .name("20TH CENTURY FOX")
                .years(Arrays.asList(2010, 2013))
                .build();
        RankingDTO ranking05 = RankingDTO
                .builder()
                .name("Netflix")
                .years(Arrays.asList(2000, 2001))
                .build();
        RankingDTO ranking06 = RankingDTO
                .builder()
                .name("Estudio Teste")
                .years(Arrays.asList(2015, 2019))
                .build();
        rankingStudios.add(ranking01);
        rankingStudios.add(ranking02);
        rankingStudios.add(ranking03);
        rankingStudios.add(ranking04);
        rankingStudios.add(ranking05);
        rankingStudios.add(ranking06);
        return rankingStudios;
    }
}