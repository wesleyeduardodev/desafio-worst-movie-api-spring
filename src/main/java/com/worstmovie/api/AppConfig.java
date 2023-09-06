package com.worstmovie.api;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "API worst movie of the year",
                version = "1.0.0-SNAPSHOT",
                description = "The API reads and stores data from a CSV file in the local H2 database. In one of its routes," +
                        "the producer returns with the longest gap between two consecutive awards, and the one\n" +
                        "got two awards faster.",
                contact = @Contact(
                        name = "Wesley Eduardo",
                        email = "wesleyeduardo.dev@gmail.com",
                        url = "https://wa.me/5598981650805"
                )
        )
)
@ApplicationPath("/api/")
public class AppConfig extends Application {
}
