package com.worstmovie.api.resources;
import com.worstmovie.api.dto.request.WorstMovieRequestDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/v1/worstmovies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "WorstMovie Resources", description = "Routes used to manipulate WorstMovie data.")
public interface WorstMovieResourceAPI {

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
    Response findAllWorstMovies(@Context UriInfo uriInfo);

    @Operation(
            description = "Return Worst Movie by Id.",
            operationId = "worstMovieResource.findSudioById",
            summary = "Return Worst Movie by Id."
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
            description = "Request executed successfully. Worst Movie data obtained."
    )
    @Path("/{id}")
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    @GET
    Response findWorstMovieById(@Context UriInfo uriInfo, @PathParam("id") Long id);

    @Operation(
            description = "Create Worst Movie.",
            operationId = "worstMovieResource.saveWorstMovie",
            summary = "Create Worst Movie."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            ref = "WorstMovieRequestDTO"
                    )
            ),
            description = "Request executed successfully. Created Worst Movie."
    )
    @POST
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "500", ref = "internalError")
    Response saveWorstMovie(@Context UriInfo uriInfo,@Valid WorstMovieRequestDTO worstMovieRequestDTO);

    @PUT
    @Path("/{id}")
    @Transactional
    @Operation(
            description = "Update Worst Movie.",
            operationId = "worstMovieResource.updateWorstMovie",
            summary = "Update Worst Movie."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            description = "Request executed successfully. Updated Worst Movie."
    )
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    Response updateWorstMovie(@PathParam("id") Long id,@Valid  WorstMovieRequestDTO worstMovieRequestDTO);

    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(
            description = "Delete Worst Movie.",
            operationId = "worstMovieResource.deleteWorstMovie",
            summary = "Delete Worst Movie."
    )
    @APIResponse(
            name = "OK",
            responseCode = "204",
            description = "Request executed successfully."
    )
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    Response deleteWorstMovie(@PathParam("id") Long id);
}
