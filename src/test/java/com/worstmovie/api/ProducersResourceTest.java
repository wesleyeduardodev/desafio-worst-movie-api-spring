package com.worstmovie.api;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class ProducersResourceTest {

    @Test
    public void testfindAllProducersEndpoint() {
        given()
                .when().get("api/v1/producers")
                .then()
                .statusCode(200);

    }
}