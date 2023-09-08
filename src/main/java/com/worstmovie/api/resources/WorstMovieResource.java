package com.worstmovie.api.resources;
import com.worstmovie.api.service.WorstMovieService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
@Path("/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "WorstMovie Resources", description = "Routes used to manipulate WorstMovie data.")
public class WorstMovieResource {

    @Inject
    WorstMovieService worstMovieService;

    @Operation(
            description = "Return all WorstMovies.",
            operationId = "worstMovieResource.findAllWorstMovies",
            summary = "Return all WorstMovies."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            ref = "WorstMovieResponseDTO"
                    )
            ),
            description = "Request executed successfully. WorstMovie data obtained."
    )
    @GET
    @Path("worstmovies")
    public Response findAllWorstMovies(@Context UriInfo uriInfo) {
        return Response.ok(worstMovieService.worstMoviesEntityToWorstMovieResponseDTO(worstMovieService.findAllWorstMovies(), uriInfo.getAbsolutePath().toString())).build();
    }
}
