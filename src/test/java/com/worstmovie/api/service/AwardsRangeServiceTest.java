package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.*;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
        MaxMinProducersAwardsRangeResponseDTO maxMinProducersAwardsRange = awardsRangeService.findProducerAwardsRange(rankingDTOS);
        List<ProducerAwardsRangeDTO> min = maxMinProducersAwardsRange.getMin().stream().sorted(Comparator.comparing(ProducerAwardsRangeDTO::getProducer)).collect(Collectors.toList());
        List<ProducerAwardsRangeDTO> max = maxMinProducersAwardsRange.getMax().stream().sorted(Comparator.comparing(ProducerAwardsRangeDTO::getProducer)).collect(Collectors.toList());
        assertEquals(min, maxMinProducersAwardsRangeMock.getMin());
        assertEquals(max, maxMinProducersAwardsRangeMock.getMax());
    }

    @Test
    public void testRangeStudiosFromMockRankingProducers() {
        List<RankingDTO> rankingDTOS = getRankingStudios();
        MaxMinStudiosAwardsRangeResponseDTO maxMinStudiosAwardsRange = awardsRangeService.findStudioAwardsRange(rankingDTOS);
        List<StudioAwardsRangeDTO> min = maxMinStudiosAwardsRange.getMin().stream().sorted(Comparator.comparing(StudioAwardsRangeDTO::getStudio)).collect(Collectors.toList());
        List<StudioAwardsRangeDTO> max = maxMinStudiosAwardsRange.getMax().stream().sorted(Comparator.comparing(StudioAwardsRangeDTO::getStudio)).collect(Collectors.toList());
        assertEquals(min, maxMinStudiosAwardsRangeMock.getMin());
        assertEquals(max, maxMinStudiosAwardsRangeMock.getMax());
    }

    private static MaxMinProducersAwardsRangeResponseDTO getMockMaxMinProducersAwardsRange() {

        ProducerAwardsRangeDTO minRangeProducerWesley = buildProducer("Wesley", 2, 1990, 1992);
        ProducerAwardsRangeDTO minRangeProducerEduardo = buildProducer("Eduardo", 2, 2005, 2007);
        List<ProducerAwardsRangeDTO> minRangesProducers = Stream.of(minRangeProducerWesley, minRangeProducerEduardo)
                .sorted(Comparator.comparing(ProducerAwardsRangeDTO::getProducer))
                .collect(Collectors.toList());

        ProducerAwardsRangeDTO maxRangeProducerAlan = buildProducer("Alan Silva", 20, 2050, 2070);
        ProducerAwardsRangeDTO maxRangeProducerCarlos = buildProducer("Carlos Silva", 20, 2000, 2020);
        List<ProducerAwardsRangeDTO> maxRangesProducers = Stream.of(maxRangeProducerAlan, maxRangeProducerCarlos)
                .sorted(Comparator.comparing(ProducerAwardsRangeDTO::getProducer))
                .collect(Collectors.toList());

        return MaxMinProducersAwardsRangeResponseDTO
                .builder()
                .min(minRangesProducers)
                .max(maxRangesProducers)
                .build();
    }

    private static MaxMinStudiosAwardsRangeResponseDTO getMockMaxMinStudiosAwardsRange() {

        StudioAwardsRangeDTO minRangeStudioWarnerBros = buildSudio("Warner Bros", 1, 1990, 1991);
        StudioAwardsRangeDTO minRangeStudioNetFlix = buildSudio("Netflix", 1, 2000, 2001);
        List<StudioAwardsRangeDTO> minRangeStudios = Stream.of(minRangeStudioWarnerBros, minRangeStudioNetFlix)
                .sorted(Comparator.comparing(StudioAwardsRangeDTO::getStudio))
                .collect(Collectors.toList());


        StudioAwardsRangeDTO maxRangeStudioParamount = buildSudio("Paramount", 20, 2010, 2030);
        StudioAwardsRangeDTO maxRangeStudioWaltDisney = buildSudio("Walt Disney", 20, 2020, 2040);
        List<StudioAwardsRangeDTO> maaxRangeStudios = Stream.of(maxRangeStudioParamount, maxRangeStudioWaltDisney)
                .sorted(Comparator.comparing(StudioAwardsRangeDTO::getStudio))
                .collect(Collectors.toList());

        return MaxMinStudiosAwardsRangeResponseDTO
                .builder()
                .min(minRangeStudios)
                .max(maaxRangeStudios)
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
        RankingDTO producer01 = RankingDTO
                .builder()
                .name("Alan Silva")
                .years(Arrays.asList(2050, 2070))
                .build();
        RankingDTO producer02 = RankingDTO
                .builder()
                .name("Wesley")
                .years(Arrays.asList(1990, 1992))
                .build();
        RankingDTO producer03 = RankingDTO
                .builder()
                .name("Carlos Silva")
                .years(Arrays.asList(2000, 2020))
                .build();
        RankingDTO producer04 = RankingDTO
                .builder()
                .name("Eduardo")
                .years(Arrays.asList(2005, 2007))
                .build();
        RankingDTO producer05 = RankingDTO
                .builder()
                .name("Gabriel")
                .years(Arrays.asList(2002, 2005))
                .build();
        RankingDTO producer06 = RankingDTO
                .builder()
                .name("André")
                .years(Arrays.asList(1990, 1994))
                .build();
        RankingDTO producer07 = RankingDTO
                .builder()
                .name("Paulo")
                .years(Arrays.asList(2000, 2004))
                .build();
        RankingDTO producer08 = RankingDTO
                .builder()
                .name("Felipe")
                .years(Arrays.asList(2003, 2007))
                .build();
        RankingDTO producer09 = RankingDTO
                .builder()
                .name("Kaick")
                .years(Arrays.asList(2001, 2005))
                .build();
        RankingDTO producer10 = RankingDTO
                .builder()
                .name("Leando")
                .years(Arrays.asList(2001, 2005))
                .build();
        rankingProducers.add(producer01);
        rankingProducers.add(producer02);
        rankingProducers.add(producer03);
        rankingProducers.add(producer04);
        rankingProducers.add(producer05);
        rankingProducers.add(producer06);
        rankingProducers.add(producer07);
        rankingProducers.add(producer08);
        rankingProducers.add(producer09);
        rankingProducers.add(producer10);
        return rankingProducers.stream().sorted(Comparator.comparing(RankingDTO::getName)).collect(Collectors.toList());
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
                .name("Estudio Teste 01")
                .years(Arrays.asList(2015, 2019))
                .build();
        RankingDTO ranking07 = RankingDTO
                .builder()
                .name("Estudio Teste 02")
                .years(Arrays.asList(2015, 2019))
                .build();
        RankingDTO ranking08 = RankingDTO
                .builder()
                .name("Estudio Teste 02")
                .years(Arrays.asList(2020, 2024))
                .build();
        RankingDTO ranking09 = RankingDTO
                .builder()
                .name("Estudio Teste 09")
                .years(Arrays.asList(2025, 2028))
                .build();
        RankingDTO ranking10 = RankingDTO
                .builder()
                .name("Estudio Teste 10")
                .years(Arrays.asList(2030, 2034))
                .build();
        rankingStudios.add(ranking01);
        rankingStudios.add(ranking02);
        rankingStudios.add(ranking03);
        rankingStudios.add(ranking04);
        rankingStudios.add(ranking05);
        rankingStudios.add(ranking06);
        rankingStudios.add(ranking07);
        rankingStudios.add(ranking08);
        rankingStudios.add(ranking09);
        rankingStudios.add(ranking10);
        return rankingStudios.stream().sorted(Comparator.comparing(RankingDTO::getName)).collect(Collectors.toList());
    }
}