package com.worstmovie.api.resources;
import com.worstmovie.api.dto.reesponse.ProducerResponseDTO;
import com.worstmovie.api.dto.request.ProducerRequestDTO;
import com.worstmovie.api.model.Producer;
import com.worstmovie.api.service.ProducersService;
import com.worstmovie.api.utils.LinksUtils;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
import java.util.Objects;

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
    public Response findAllProducers(@Context UriInfo uriInfo) {
        return Response.ok(producersService.producersEntityToProducersResponseDTO(Producer.listAll(), uriInfo.getAbsolutePath().toString())).build();
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
    public Response findProducerById(@Context UriInfo uriInfo, @PathParam("id") Long id) {
        Producer producer = Producer.findById(id);
        Response response;
        if (Objects.nonNull(producer)) {
            response = Response.ok(producersService.producersEntityToProducerResponse(producer, uriInfo.getAbsolutePath().toString())).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }

    @Operation(
            description = "Create producer.",
            operationId = "producersResource.saveProducers",
            summary = "Create a new producer."
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
            description = "Request executed successfully. Created producer."
    )
    @POST
    public Response saveProducer(@Context UriInfo uriInfo, ProducerRequestDTO producersRequestDTO) {
        Response response;
        try {
            Producer producer = producersService.saveProducer(producersRequestDTO.getName());
            ProducerResponseDTO producerResponseDTO = ProducerResponseDTO
                    .builder()
                    .id(producer.getId())
                    .name(producer.getName())
                    .links(LinksUtils.generateLinks(uriInfo.getAbsolutePath().toString(), producer.getId()))
                    .build();
            response = Response.ok(producerResponseDTO).build();
        } catch (Exception e) {
            response = Response.serverError().build();
        }
        return response;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Operation(
            description = "Update producer.",
            operationId = "producersResource.updateProducer",
            summary = "Update Producer."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            description = "Request executed successfully. Updated Producer."
    )
    public Response updateProducer(@PathParam("id") Long id, ProducerRequestDTO producersRequestDTO) {
        Producer producer = Producer.findById(id);
        Response response;
        if (Objects.nonNull(producer)) {
            producersService.updateProducers(id, producersRequestDTO);
            response = Response.ok().build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(
            description = "Delete producer.",
            operationId = "producersResource.deleteProducer",
            summary = "Delete producer."
    )
    @APIResponse(
            name = "OK",
            responseCode = "204",
            description = "Request executed successfully."
    )
    public Response deleteProducer(@PathParam("id") Long id) {
        producersService.deleteProducers(id);
        return Response.noContent().build();
    }
}
