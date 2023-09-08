package com.worstmovie.api.resources;
import com.worstmovie.api.dto.request.ProducerRequestDTO;
import com.worstmovie.api.model.Producer;
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

@Path("/v1/producers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Producer Resources", description = "Routes used to manipulate producer data.")
public class ProducerResource {

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
                            ref = "ProducerResponseDTO"
                    )
            ),
            description = "Request executed successfully. Producer data obtained."
    )
    @GET
    public Response findAllProducers() {
        return Response.ok(producersService.producersEntityToProducersResponseDTO(Producer.listAll())).build();
    }

    @Operation(
            description = "Return Producers by Id.",
            operationId = "producersResource.findProducerById",
            summary = "Return Producers by Id."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            ref = "ProducerResponseDTO"
                    )
            ),
            description = "Request executed successfully. Producer data obtained."
    )
    @Path("/{id}")
    @GET
    public Response findProducerById(@PathParam("id") Long id) {
        return Response.ok(producersService.producersEntityToProducerResponse(Producer.findById(id))).build();
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
                            ref = "ProducerRequestDTO"
                    )
            ),
            description = "Request executed successfully. Created producer."
    )
    @POST
    public Response saveProducer(ProducerRequestDTO producersRequestDTO) {
        Response response;
        try {
            Producer producer = producersService.saveProducer(producersRequestDTO.getName());
            response = Response.ok(producer).build();
        } catch (Exception e) {
            response = Response.serverError().build();
        }
        return response;
    }
}
