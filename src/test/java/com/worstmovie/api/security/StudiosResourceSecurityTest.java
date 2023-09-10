package com.worstmovie.api.security;
import com.worstmovie.api.dto.request.StudioRequestDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class StudiosResourceSecurityTest {

    private static final String PATH_STUDIOS_RESOURCE = "/api/v1/studios";
    private static final String PATH_STUDIOS_RESOURCE_WITH_ID = PATH_STUDIOS_RESOURCE.concat("/1");

    @Test
    void shouldAccessEndpointFindAllStudios() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .when()
                .get(PATH_STUDIOS_RESOURCE)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void shouldAccessEndpointSaveStudios() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(StudioRequestDTO.class)
                .when()
                .post(PATH_STUDIOS_RESOURCE)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void notShouldAccessEndpointSaveStudios() {
        given()
                .auth()
                .preemptive()
                .basic("invalid_user", "invalid_password ")
                .contentType(ContentType.JSON)
                .body(StudioRequestDTO.class)
                .when()
                .post(PATH_STUDIOS_RESOURCE)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void shouldAccessEndpointUpdateStudios() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(StudioRequestDTO.class)
                .when()
                .put(PATH_STUDIOS_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void notShouldAccessEndpointUpdateStudios() {
        given()
                .auth()
                .preemptive()
                .basic("invalid_user", "invalid_password ")
                .contentType(ContentType.JSON)
                .body(StudioRequestDTO.class)
                .when()
                .put(PATH_STUDIOS_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void shouldAccessEndpointDeleteStudios() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .when()
                .delete(PATH_STUDIOS_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    void notShouldAccessEndpointDeleteStudios() {
        given()
                .auth()
                .preemptive()
                .basic("invalid_user", "invalid_password ")
                .when()
                .delete(PATH_STUDIOS_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
