package com.worstmovie.api.resources;
import com.worstmovie.api.dto.request.WorstMovieRequestDTO;
import com.worstmovie.api.dto.response.WorstMovieResponseDTO;
import com.worstmovie.api.model.WorstMovie;
import com.worstmovie.api.service.WorstMovieService;
import com.worstmovie.api.utils.LinksUtils;
import com.worstmovie.api.utils.ResponseMapperUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.resteasy.reactive.RestResponse;
import java.util.Optional;

public class WorstMovieResource implements WorstMovieResourceAPI {

    @Inject
    WorstMovieService worstMovieService;

    @Override
    @PermitAll
    public Response findAllWorstMovies(@Context UriInfo uriInfo) {
        return Response.ok(worstMovieService.toWorstMoviesResponseDTO(worstMovieService.findAllWorstMovies(), uriInfo.getAbsolutePath().toString())).build();
    }

    @Override
    @PermitAll
    public Response findWorstMovieById(UriInfo uriInfo, Long id) {
        Optional<WorstMovie> worstMovie = WorstMovie.findByIdOptional(id);
        Response response;
        if (worstMovie.isPresent()) {
            response = Response.ok(worstMovieService.toWorstMovieResponseDTO(worstMovie.get(), uriInfo.getAbsolutePath().toString())).build();
        } else {
            response = ResponseMapperUtils.notFound();
        }
        return response;
    }

    @Override
    @RolesAllowed("admin")
    public Response saveWorstMovie(UriInfo uriInfo, WorstMovieRequestDTO worstMovieRequestDTO) {
        WorstMovie worstMovie = worstMovieService.saveWorstMovie(worstMovieRequestDTO.getYear(), worstMovieRequestDTO.getTitle(), worstMovieRequestDTO.getWinner());
        WorstMovieResponseDTO worstMovieResponseDTO = WorstMovieResponseDTO
                .builder()
                .id(worstMovie.getId())
                .year(worstMovie.getYear())
                .title(worstMovie.getTitle())
                .winner(worstMovie.isWinner())
                .links(LinksUtils.generateLinks(uriInfo.getAbsolutePath().toString(), worstMovie.getId()))
                .build();
        return Response.status(RestResponse.Status.CREATED).entity(worstMovieResponseDTO).build();
    }

    @Override
    @RolesAllowed("admin")
    public Response updateWorstMovie(Long id, WorstMovieRequestDTO worstMovieRequestDTO) {
        Optional<WorstMovie> worstMovie = WorstMovie.findByIdOptional(id);
        Response response;
        if (worstMovie.isPresent()) {
            worstMovieService.updateWorstMovie(id, worstMovieRequestDTO);
            response = Response.ok().build();
        } else {
            response = ResponseMapperUtils.badRequest("01", "WorstMovie not found");
        }
        return response;
    }

    @Override
    @Transactional
    @RolesAllowed("admin")
    public Response deleteWorstMovie(Long id) {
        Optional<WorstMovie> worstMovie = WorstMovie.findByIdOptional(id);
        Response response;
        if (worstMovie.isPresent()) {
            worstMovieService.deleteWorstMovie(worstMovie.get().getId());
            response = ResponseMapperUtils.noContent();
        } else {
            response = ResponseMapperUtils.notFound();
        }
        return response;
    }
}
