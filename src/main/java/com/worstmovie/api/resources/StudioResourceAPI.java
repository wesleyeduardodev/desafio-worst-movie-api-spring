package com.worstmovie.api.resources;
import com.worstmovie.api.dto.request.StudioRequestDTO;
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

@Path("/v1/studios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Studio Resources", description = "Routes used to manipulate studio data.")
@SecurityRequirements(value = {@SecurityRequirement(name = "basicAuth")})
public interface StudioResourceAPI {

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
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    @GET
    Response findAllStudios(@Context UriInfo uriInfo);

    @Operation(
            description = "Return Studio by Id.",
            operationId = "studioResource.findSudioById",
            summary = "Return Studio by Id."
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
    @Path("/{id}")
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    @GET
    Response findSudioById(@Context UriInfo uriInfo, @PathParam("id") Long id);

    @Operation(
            description = "Create Studio.",
            operationId = "studioResource.saveStudio",
            summary = "Create a new Studio."
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
            description = "Request executed successfully. Created Studio."
    )
    @POST
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "500", ref = "internalError")
    Response saveStudio(@Context UriInfo uriInfo, @Valid StudioRequestDTO studioRequestDTO);

    @PUT
    @Path("/{id}")
    @Transactional
    @Operation(
            description = "Update Studio.",
            operationId = "studioResource.updateStudio",
            summary = "Update Studio."
    )
    @APIResponse(
            name = "OK",
            responseCode = "200",
            description = "Request executed successfully. Updated Studio."
    )
    @APIResponse(responseCode = "400", ref = "illegalRequest")
    @APIResponse(responseCode = "401", ref = "unauthorized")
    @APIResponse(responseCode = "403", ref = "forbiden")
    @APIResponse(responseCode = "404", ref = "notFound")
    @APIResponse(responseCode = "500", ref = "internalError")
    Response updateStudio(@PathParam("id") Long id, @Valid StudioRequestDTO studioRequestDTO);


    @DELETE
    @Path("/{id}")
    @Transactional
    @Operation(
            description = "Delete Studio.",
            operationId = "studioResource.deleteStudio",
            summary = "Delete Studio."
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
    Response deleteStudio(@PathParam("id") Long id);
}
