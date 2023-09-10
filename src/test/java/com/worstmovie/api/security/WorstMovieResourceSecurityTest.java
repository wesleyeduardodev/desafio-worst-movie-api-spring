package com.worstmovie.api.security;
import com.worstmovie.api.dto.request.WorstMovieRequestDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class WorstMovieResourceSecurityTest {

    private static final String PATH_WORT_MOVIE_RESOURCE = "/api/v1/worstmovies";
    private static final String PATH_WORTS_MOVIE_RESOURCE_WITH_ID = PATH_WORT_MOVIE_RESOURCE.concat("/1");

    @Test
    void shouldAccessEndpointFindAllWorstMovie() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .when()
                .get(PATH_WORT_MOVIE_RESOURCE)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void shouldAccessEndpointSaveWorstMovie() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(WorstMovieRequestDTO.class)
                .when()
                .post(PATH_WORT_MOVIE_RESOURCE)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void notShouldAccessEndpointSaveWorstMovie() {
        given()
                .auth()
                .preemptive()
                .basic("invalid_user", "invalid_password ")
                .contentType(ContentType.JSON)
                .body(WorstMovieRequestDTO.class)
                .when()
                .post(PATH_WORT_MOVIE_RESOURCE)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void shouldAccessEndpointUpdateWorstMovie() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .contentType(ContentType.JSON)
                .body(WorstMovieRequestDTO.class)
                .when()
                .put(PATH_WORTS_MOVIE_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void notShouldAccessEndpointUpdateWorstMovie() {
        given()
                .auth()
                .preemptive()
                .basic("invalid_user", "invalid_password ")
                .contentType(ContentType.JSON)
                .body(WorstMovieRequestDTO.class)
                .when()
                .put(PATH_WORTS_MOVIE_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void shouldAccessEndpointDeleteWorstMovie() {
        given()
                .auth()
                .preemptive()
                .basic("admin", "admin")
                .when()
                .delete(PATH_WORTS_MOVIE_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    void notShouldAccessEndpointDeleteWorstMovie() {
        given()
                .auth()
                .preemptive()
                .basic("invalid_user", "invalid_password ")
                .when()
                .delete(PATH_WORTS_MOVIE_RESOURCE_WITH_ID)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
