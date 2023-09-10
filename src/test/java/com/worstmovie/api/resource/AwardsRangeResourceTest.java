package com.worstmovie.api.resource;
import com.worstmovie.api.dto.response.MaxMinStudiosAwardsRangeResponseDTO;
import com.worstmovie.api.dto.response.ProducerAwardsRangeDTO;
import com.worstmovie.api.dto.response.MaxMinProducersAwardsRangeResponseDTO;
import com.worstmovie.api.dto.response.StudioAwardsRangeDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class AwardsRangeResourceTest {

    private static final String PATH_AWARDSRANGE_PRODUCERS = "/api/v1/awardsrange/producers";
    private static final String PATH_AWARDSRANGE_STUDIOS = "/api/v1/awardsrange/studios";
    private static MaxMinProducersAwardsRangeResponseDTO maxMinProducersAwardsRangeMock;
    private static MaxMinStudiosAwardsRangeResponseDTO maxMinStudiosAwardsRangeMock;

    @BeforeAll
    public static void setup() {
        maxMinProducersAwardsRangeMock = getMockMaxMinProducersAwardsRange();
        maxMinStudiosAwardsRangeMock = getMockMaxMinStudiosAwardsRange();
    }

    @Test
    public void testeResponseOkFindProducerAwardRange() {
        given()
                .when().get(PATH_AWARDSRANGE_PRODUCERS)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testeResponseOkFindStudiosAwardRange() {
        given()
                .when().get(PATH_AWARDSRANGE_STUDIOS)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testResponseFindProducerAwardsRangeFromCsvDataset() {
        MaxMinProducersAwardsRangeResponseDTO maxMinProducersAwardsRange = given()
                .when().get(PATH_AWARDSRANGE_PRODUCERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(MaxMinProducersAwardsRangeResponseDTO.class);
        assertNotNull(maxMinProducersAwardsRange);
        assertEquals(maxMinProducersAwardsRange.getMin(), maxMinProducersAwardsRangeMock.getMin());
        assertEquals(maxMinProducersAwardsRange.getMax(), maxMinProducersAwardsRangeMock.getMax());
    }

    @Test
    public void testResponseFindAwardsRangeStudiosFromCsvDataset() {
        MaxMinStudiosAwardsRangeResponseDTO maxMinStudiosAwardsRange = given()
                .when().get(PATH_AWARDSRANGE_STUDIOS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(MaxMinStudiosAwardsRangeResponseDTO.class);
        assertNotNull(maxMinStudiosAwardsRange);
        List<StudioAwardsRangeDTO> min = maxMinStudiosAwardsRange.getMin().stream().sorted(Comparator.comparing(StudioAwardsRangeDTO::getStudio)).collect(Collectors.toList());
        assertEquals(min, maxMinStudiosAwardsRangeMock.getMin());
        assertEquals(maxMinStudiosAwardsRange.getMax(), maxMinStudiosAwardsRangeMock.getMax());
    }

    private static MaxMinProducersAwardsRangeResponseDTO getMockMaxMinProducersAwardsRange() {
        ProducerAwardsRangeDTO minRangeProducer = buildProducer("JOEL SILVER", 1, 1990, 1991);
        ProducerAwardsRangeDTO maxRangeProducer = buildProducer("MATTHEW VAUGHN", 13, 2002, 2015);
        return MaxMinProducersAwardsRangeResponseDTO
                .builder()
                .min(Collections.singletonList(minRangeProducer))
                .max(Collections.singletonList(maxRangeProducer))
                .build();
    }

    private static MaxMinStudiosAwardsRangeResponseDTO getMockMaxMinStudiosAwardsRange() {

        StudioAwardsRangeDTO minRangerColumbiaStudio = buildSudio("COLUMBIA PICTURES", 1, 2017, 2018);
        StudioAwardsRangeDTO minRangerParamountStudio = buildSudio("PARAMOUNT PICTURES", 1, 2008, 2009);
        StudioAwardsRangeDTO minRangerParamountStudio2 = buildSudio("PARAMOUNT PICTURES", 1, 2009, 2010);
        StudioAwardsRangeDTO minRangerWarnerBrosStudio = buildSudio("WARNER BROS.", 1, 1999, 2000);

        List<StudioAwardsRangeDTO> minRangesProducers = Stream.of(minRangerColumbiaStudio, minRangerParamountStudio, minRangerParamountStudio2, minRangerWarnerBrosStudio)
                .sorted(Comparator.comparing(StudioAwardsRangeDTO::getStudio))
                .collect(Collectors.toList());

        StudioAwardsRangeDTO maxRangerParamountStudio = buildSudio("PARAMOUNT PICTURES", 15, 1993, 2008);
        return MaxMinStudiosAwardsRangeResponseDTO
                .builder()
                .min(minRangesProducers)
                .max(Collections.singletonList(maxRangerParamountStudio))
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
}