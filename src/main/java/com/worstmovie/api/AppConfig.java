package com.worstmovie.api;
import com.worstmovie.api.exceptions.ApiErrorDTO;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@OpenAPIDefinition(
        info = @Info(
                title = "API worst movie of the year - Golden Raspberry Awards",
                version = "1.0.0-SNAPSHOT",
                description = "The API reads and stores data from a CSV file in the local H2 database. In one of its routes," +
                        "the producer returns with the longest gap between two consecutive awards, and the one\n" +
                        "got two awards faster.",
                contact = @Contact(
                        name = "Wesley Eduardo",
                        email = "wesleyeduardo.dev@gmail.com",
                        url = "https://wa.me/5598981650805"
                )
        ),
        components = @Components(
                responses = {
                        @APIResponse(
                                name = "noContent",
                                responseCode = "204",
                                description = "Content not found"
                        ),
                        @APIResponse(
                                name = "illegalRequest",
                                responseCode = "400",
                                description = "Invalid request",
                                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "apiError"))
                        ),
                        @APIResponse(
                                name = "unauthorized",
                                responseCode = "401",
                                description = "unauthorized"
                        ),
                        @APIResponse(
                                name = "forbiden",
                                responseCode = "403",
                                description = "forbiden"
                        ),
                        @APIResponse(
                                name = "notFound",
                                responseCode = "404",
                                description = "not found"
                        ),
                        @APIResponse(
                                name = "internalError",
                                responseCode = "500",
                                description = "internal server error",
                                content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "apiError"))
                        ),
                        @APIResponse(
                                name = "serviceUnavailable",
                                responseCode = "503",
                                description = "unavailable service"
                        )
                },
                schemas = {
                        @Schema(
                                description = "object used to map request errors",
                                name = "apiError",
                                type = SchemaType.OBJECT,
                                implementation = ApiErrorDTO.class
                        )
                },
                securitySchemes = {
                        @SecurityScheme(
                                securitySchemeName = "basicAuth",
                                type = SecuritySchemeType.HTTP,
                                scheme = "basic"
                        )
                }
        )
)
@ApplicationPath("/api/")
public class AppConfig extends Application {
}
