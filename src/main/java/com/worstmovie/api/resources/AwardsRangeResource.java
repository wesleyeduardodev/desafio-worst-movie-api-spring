package com.worstmovie.api.resources;
import com.worstmovie.api.service.AwardsRangeService;;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/v1/awardsrange")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Awards Range Resource ", description = "Routes used to return Awards Range.")
public class AwardsRangeResource {

    @Inject
    AwardsRangeService awardsRangeService;

    @Operation(
            description = "Return Awards Range Producers.",
            operationId = "awardsRangeResource.findAwardsRangeProducer",
            summary = "Return Awards Range Producers."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            ref = "MaxMinAwardsRangeDTO"
                    )
            ),
            description = "Request executed successfully."
    )
    @GET
    @Path("/producers")
    public Response findAwardsRangeProducer() {
        return Response.ok(awardsRangeService.findAwardsRangeProducer()).build();
    }

    @Operation(
            description = "Essa rota serve apenas para indicar uma possível evolução futura da API. Onde seja possível também retornar\n" +
                          "Studio com maior intervalo entre dois prêmios consecutivos. No momento essa rota não retornará dados.",
            operationId = "awardsRangeResource.findAwardsRangeStudios",
            summary = "Return Awards Range Studios.."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            ref = "MaxMinAwardsRangeDTO"
                    )
            ),
            description = "Request executed successfully."
    )
    @GET
    @Path("/studios")
    public Response findAwardsRangeStudios() {
        //TODO Essa rota serve apenas para indicar uma possível evolução futura da API. Onde seja possível também retornar
        // o Studio com maior intervalo entre dois prêmios consecutivos. No momento essa rota não retornará dados.
        return Response.ok().build();
    }
}
