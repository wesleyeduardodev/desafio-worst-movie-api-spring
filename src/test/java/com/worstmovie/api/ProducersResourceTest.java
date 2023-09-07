package com.worstmovie.api;
import com.worstmovie.api.dto.response.MaxMinAwardsRangeDTO;
import com.worstmovie.api.service.AwardsRangeService;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ProducersResourceTest {

    @InjectMocks
    AwardsRangeService awardsRangeServiceMock;

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
        MaxMinAwardsRangeDTO maxMinAwardsRangeDTO = given()
                .when().get(PATH_AWARDSRANGE_PRODUCERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(MaxMinAwardsRangeDTO.class);
        assertNotNull(maxMinAwardsRangeDTO);
    }
}