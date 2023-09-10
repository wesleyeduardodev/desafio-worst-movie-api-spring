package com.worstmovie.api.security;
import com.worstmovie.api.dto.request.ProducerRequestDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class ProducersResourceSecurityTest {

    private static final String PATH_PRODUCERS_RESOURCE = "/api/v1/producers";
    private static final String PATH_PRODUCERS_RESOURCE_WITH_ID = PATH_PRODUCERS_RESOURCE.concat("/1");

    @Test
    void shouldAccessEndpointFindAllProducer() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .when()
                .get(PATH_PRODUCERS_RESOURCE)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void shouldAccessEndpointFindProducerById() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .when()
                .get(PATH_PRODUCERS_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void shouldAccessEndpointSaveProducer() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(ProducerRequestDTO.class)
                .when()
                .post(PATH_PRODUCERS_RESOURCE)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void notShouldAccessEndpointSaveProducer() {
        given()
                .auth()
                .preemptive()
                .basic("invalid_user", "invalid_password ")
                .contentType(ContentType.JSON)
                .body(ProducerRequestDTO.class)
                .when()
                .post(PATH_PRODUCERS_RESOURCE)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void shouldAccessEndpointUpdateProducer() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(ProducerRequestDTO.class)
                .when()
                .put(PATH_PRODUCERS_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void notShouldAccessEndpointUpdateProducer() {
        given()
                .auth()
                .preemptive()
                .basic("invalid_user", "invalid_password ")
                .contentType(ContentType.JSON)
                .body(ProducerRequestDTO.class)
                .when()
                .put(PATH_PRODUCERS_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void shouldAccessEndpointDeleteProducer() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .when()
                .delete(PATH_PRODUCERS_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    void notShouldAccessEndpointDeleteProducer() {
        given()
                .auth()
                .preemptive()
                .basic("invalid_user", "invalid_password ")
                .when()
                .delete(PATH_PRODUCERS_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
