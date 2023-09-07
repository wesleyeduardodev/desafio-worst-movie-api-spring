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
import java.util.ArrayList;

@Path("/v1/awardsrange")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Awards Range Resource ", description = "Routes used to return Awards Range.")
public class AwardsRangeResource {

    @Inject
    AwardsRangeService awardsRangeService;

    @Operation(
            description = "Return Interval Awards.",
            operationId = "awardsRangeResource.findAwardsRangeProducer",
            summary = "Return Interval Awards."
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

    @GET
    @Path("/studios")
    public Response findAwardsRangeStudios() {
        return Response.ok(new ArrayList<>()).build();
    }
}
