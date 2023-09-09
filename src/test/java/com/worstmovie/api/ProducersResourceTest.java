package com.worstmovie.api;
import com.worstmovie.api.dto.response.ProducerAwardsRangeDTO;
import com.worstmovie.api.dto.response.MaxMinProducersAwardsRangeResponseDTO;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ProducersResourceTest {

    private static final String PATH_AWARDSRANGE_PRODUCERS = "/api/v1/awardsrange/producers";

    @Test
    public void testeResponseOkReturnAwardsRangeProducer() {
        given()
                .when().get(PATH_AWARDSRANGE_PRODUCERS)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testResponseMaxMinAwardsRangeDTO() {
        MaxMinProducersAwardsRangeResponseDTO maxMinAwardsRangeDTO = given()
                .when().get(PATH_AWARDSRANGE_PRODUCERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(MaxMinProducersAwardsRangeResponseDTO.class);
        assertNotNull(maxMinAwardsRangeDTO);
        assertEquals(maxMinAwardsRangeDTO.getMax(), getMockMaxMinAwardsRangeDTO().getMax());
        assertEquals(maxMinAwardsRangeDTO.getMin(), getMockMaxMinAwardsRangeDTO().getMin());
    }

    private MaxMinProducersAwardsRangeResponseDTO getMockMaxMinAwardsRangeDTO() {
        ProducerAwardsRangeDTO minRangeProducer = ProducerAwardsRangeDTO
                .builder()
                .producer("JOEL SILVER")
                .interval(1)
                .previousWin(1990)
                .followingWin(1991)
                .build();
        ProducerAwardsRangeDTO maxRangeProducer = ProducerAwardsRangeDTO
                .builder()
                .producer("MATTHEW VAUGHN")
                .interval(13)
                .previousWin(2002)
                .followingWin(2015)
                .build();
        return MaxMinProducersAwardsRangeResponseDTO
                .builder()
                .min(Collections.singletonList(minRangeProducer))
                .max(Collections.singletonList(maxRangeProducer))
                .build();
    }
}