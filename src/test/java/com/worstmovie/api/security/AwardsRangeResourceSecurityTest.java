package com.worstmovie.api.security;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;

@QuarkusTest
public class AwardsRangeResourceSecurityTest {

    private static final String PATH_PRODUCERS_AWARD_RANGE = "/api/v1/awardsrange/producers";
    private static final String PATH_STUDIOS_AWARD_RANGE = "/api/v1/awardsrange/studios";

    @Test
    void shouldAccessProducersAwardRangePublicEndpoint() {
        get(PATH_PRODUCERS_AWARD_RANGE)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void shouldAccessStudiosAwardRangePublicEndpoint() {
        get(PATH_STUDIOS_AWARD_RANGE)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
