package com.worstmovie.api.resources;
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
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirements;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;;

@Path("/v1/awardsrange")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Awards Range Resource ", description = "Routes used to return Awards Range.")
@SecurityRequirements(value = {@SecurityRequirement(name = "basicAuth")})
public interface AwardsRangeResourceAPI {

    @Operation(
            description = "Essa rota serve para obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que\n" +
                    "obteve dois prêmios mais rápido.",
            operationId = "awardsRangeResource.findAwardsRangeProducer",
            summary = "Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que\n" +
                    "obteve dois prêmios mais rápido."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            ref = "MaxMinProducersAwardsRangeResponseDTO"
                    )
            ),
            description = "Request executed successfully."
    )
    @GET
    @Path("/producers")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "500", ref = "internalError")
    Response findAwardsRangeProducer();

    @Operation(
            description = "Essa rota serve para obter o Studio com maior intervalo entre dois prêmios consecutivos, e o que\n" +
                    "obteve dois prêmios mais rápido.",
            operationId = "awardsRangeResource.findAwardsRangeStudios",
            summary = "Obter o Studio com maior intervalo entre dois prêmios consecutivos, e o que\n" +
                    "obteve dois prêmios mais rápido."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                            ref = "MaxMinStudiosAwardsRangeResponseDTO"
                    )
            ),
            description = "Request executed successfully."
    )
    @GET
    @Path("/studios")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "500", ref = "internalError")
    Response findAwardsRangeStudios();
}
