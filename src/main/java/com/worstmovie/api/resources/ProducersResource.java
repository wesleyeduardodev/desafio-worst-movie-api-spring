package com.worstmovie.api.resources;
import com.worstmovie.api.dto.request.ProducersRequestDTO;
import com.worstmovie.api.service.ProducersService;
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
@Tag(name = "Producer Resources.", description = "Routes used to manipulate producer data.")
public class ProducersResource {

    @Inject
    ProducersService producersService;

    @Operation(
            description = "Return all producers.",
            operationId = "producersResource.findAllProducers",
            summary = "Return all producers."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            ref = "ProducersResponseDTO"
                    )
            ),
            description = "Request executed successfully. Producer data obtained."
    )
    @GET
    @Path("producers")
    public Response findAllProducers() {
        return Response.ok(producersService.producersEntityToProducersResponseDTO(producersService.findAllProducers())).build();
    }

    @Operation(
            description = "Create producer.",
            operationId = "producersResource.saveProducers",
            summary = "Create a new producer record."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            ref = "ProducersRequestDTO"
                    )
            ),
            description = "Request executed successfully. Created producer."
    )
    @POST
    @Path("producers")
    public Response saveProducers(ProducersRequestDTO producersRequestDTO) {
        Response response;
        try {
            producersService.saveProducer(producersRequestDTO);
            response = Response.ok().build();
        } catch (Exception e) {
            response = Response.serverError().build();
        }
        return response;
    }
}
