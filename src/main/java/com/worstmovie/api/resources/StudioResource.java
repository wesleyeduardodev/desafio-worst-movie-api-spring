package com.worstmovie.api.resources;
import com.worstmovie.api.dto.response.StudioResponseDTO;
import com.worstmovie.api.dto.request.StudioRequestDTO;
import com.worstmovie.api.model.Studio;
import com.worstmovie.api.service.StudiosService;
import com.worstmovie.api.utils.LinksUtils;
import com.worstmovie.api.utils.ResponseMapperUtils;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestResponse;
import java.util.Optional;

public class StudioResource implements StudioResourceAPI {

    @Inject
    StudiosService studiosService;

    @Override
    public Response findAllStudios(@Context UriInfo uriInfo) {
        return Response.ok(studiosService.studiosEntityToStudiosResponseDTO(studiosService.findAllStudios(), uriInfo.getAbsolutePath().toString())).build();
    }

    @Override
    public Response findSudioById(@Context UriInfo uriInfo, @PathParam("id") Long id) {
        Optional<Studio> studio = Studio.findByIdOptional(id);
        Response response;
        if (studio.isPresent()) {
            response = Response.ok(studiosService.toStudioResponseDTO(studio.get(), uriInfo.getAbsolutePath().toString())).build();
        } else {
            response = ResponseMapperUtils.notFound();
        }
        return response;
    }

    @Override
    public Response saveStudio(@Context UriInfo uriInfo, StudioRequestDTO studioRequestDTO) {
        Studio studio = studiosService.saveStudio(studioRequestDTO.getName());
        StudioResponseDTO studioResponse = StudioResponseDTO
                .builder()
                .id(studio.getId())
                .name(studio.getName())
                .links(LinksUtils.generateLinks(uriInfo.getAbsolutePath().toString(), studio.getId()))
                .build();
        return Response.status(RestResponse.Status.CREATED).entity(studioResponse).build();
    }

    @Override
    public Response updateStudio(@PathParam("id") Long id, StudioRequestDTO studioRequestDTO) {
        Optional<Studio> studio = Studio.findByIdOptional(id);
        Response response;
        if (studio.isPresent()) {
            studiosService.updateStudio(id, studioRequestDTO);
            response = Response.ok().build();
        } else {
            response = ResponseMapperUtils.badRequest("01", "Studio not found");
        }
        return response;
    }

    @Override
    @Transactional
    public Response deleteStudio(@PathParam("id") Long id) {
        Optional<Studio> studio = Studio.findByIdOptional(id);
        Response response;
        if (studio.isPresent()) {
            studiosService.deleteStudio(studio.get().getId());
            response = ResponseMapperUtils.noContent();
        } else {
            response = ResponseMapperUtils.notFound();
        }
        return response;
    }
}
