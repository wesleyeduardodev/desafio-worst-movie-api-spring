package com.worstmovie.api.resources;
import com.worstmovie.api.dto.reesponse.StudioResponseDTO;
import com.worstmovie.api.dto.request.StudioRequestDTO;
import com.worstmovie.api.model.Studio;
import com.worstmovie.api.service.StudiosService;
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
import org.eclipse.microprofile.openapi.annotations.tags.Tag;import java.util.Objects;

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
    public Response findAllStudios(@Context UriInfo uriInfo) {
        return Response.ok(studiosService.studiosEntityToStudiosResponseDTO(studiosService.findAllStudios(), uriInfo.getAbsolutePath().toString())).build();
    }

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
    @GET
    public Response findSudioById(@Context UriInfo uriInfo, @PathParam("id") Long id) {
        Studio studio = Studio.findById(id);
        Response response;
        if (Objects.nonNull(studio)) {
            response = Response.ok(studiosService.studioEntityToStudioResponse(studio, uriInfo.getAbsolutePath().toString())).build();
        } else {
            response = Response.noContent().build();
        }
        return response;
    }

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
    public Response saveStudio(@Context UriInfo uriInfo, StudioRequestDTO studioRequestDTO) {
        Response response;
        try {
            Studio studio = studiosService.saveStudio(studioRequestDTO.getName());
            StudioResponseDTO studioResponseDTO = StudioResponseDTO
                    .builder()
                    .id(studio.getId())
                    .name(studio.getName())
                    .links(LinksUtils.generateLinks(uriInfo.getAbsolutePath().toString(), studio.getId()))
                    .build();
            response = Response.ok(studioResponseDTO).build();
        } catch (Exception e) {
            response = Response.serverError().build();
        }
        return response;
    }

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
    public Response updateStudio(@PathParam("id") Long id, StudioRequestDTO studioRequestDTO) {
        Studio studio = Studio.findById(id);
        Response response;
        if (Objects.nonNull(studio)) {
            studiosService.updateStudio(id, studioRequestDTO);
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
            description = "Delete Studio.",
            operationId = "producersResource.deleteStudio",
            summary = "Delete Studio."
    )
    @APIResponse(
            name = "OK",
            responseCode = "204",
            description = "Request executed successfully."
    )
    public Response deleteStudio(@PathParam("id") Long id) {
        studiosService.deleteStudio(id);
        return Response.noContent().build();
    }
}
