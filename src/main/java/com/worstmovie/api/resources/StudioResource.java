package com.worstmovie.api.resources;
import com.worstmovie.api.service.StudiosService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Studio Resources", description = "Routes used to manipulate studio data.")
public class StudioResource {

    @Inject
    StudiosService studiosService;

    @Operation(
            description = "Return all studios.",
            operationId = "studioResource.findAllStudios",
            summary = "Return all studios."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            ref = "StudioResponseDTO"
                    )
            ),
            description = "Request executed successfully. Studio data obtained."
    )
    @GET
    @Path("studios")
    public Response findAllStudios() {
        return Response.ok(studiosService.studiosEntityToStudiosResponseDTO(studiosService.findAllStudios())).build();
    }
}
