package com.worstmovie.api;
import com.worstmovie.api.dto.response.AwardsRangeDTO;
import com.worstmovie.api.dto.response.MaxMinAwardsRangeResponseDTO;
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
        MaxMinAwardsRangeResponseDTO maxMinAwardsRangeDTO = given()
                .when().get(PATH_AWARDSRANGE_PRODUCERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(MaxMinAwardsRangeResponseDTO.class);
        assertNotNull(maxMinAwardsRangeDTO);
        assertEquals(maxMinAwardsRangeDTO.getMax(), getMockMaxMinAwardsRangeDTO().getMax());
        assertEquals(maxMinAwardsRangeDTO.getMin(), getMockMaxMinAwardsRangeDTO().getMin());
    }

    private MaxMinAwardsRangeResponseDTO getMockMaxMinAwardsRangeDTO() {
        AwardsRangeDTO minRangeProducer = AwardsRangeDTO
                .builder()
                .producer("JOEL SILVER")
                .interval(1)
                .previousWin(1990)
                .followingWin(1991)
                .build();
        AwardsRangeDTO maxRangeProducer = AwardsRangeDTO
                .builder()
                .producer("MATTHEW VAUGHN")
                .interval(13)
                .previousWin(2002)
                .followingWin(2015)
                .build();
        return MaxMinAwardsRangeResponseDTO
                .builder()
                .min(Collections.singletonList(minRangeProducer))
                .max(Collections.singletonList(maxRangeProducer))
                .build();
    }
}