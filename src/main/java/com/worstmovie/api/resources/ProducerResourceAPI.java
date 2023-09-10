package com.worstmovie.api.resources;
import com.worstmovie.api.dto.request.ProducerRequestDTO;
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
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirements;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/v1/producers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Producer Resources", description = "Routes used to manipulate producer data.")
@SecurityRequirements(value = {@SecurityRequirement(name = "basicAuth")})
public interface ProducerResourceAPI {

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
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    @GET
    Response findAllProducers(@Context UriInfo uriInfo);

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
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    @GET
    Response findProducerById(@Context UriInfo uriInfo, @PathParam("id") Long id);

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
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "500", ref = "internalError")
    Response saveProducer(@Context UriInfo uriInfo, @Valid ProducerRequestDTO producersRequestDTO);

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
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    Response updateProducer(@PathParam("id") Long id, @Valid ProducerRequestDTO producersRequestDTO);

    @DELETE
    @Path("/{id}")
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
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    Response deleteProducer(@PathParam("id") Long id);
}
