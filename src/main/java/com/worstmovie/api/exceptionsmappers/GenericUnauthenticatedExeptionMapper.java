package com.worstmovie.api.exceptionsmappers;
import com.worstmovie.api.utils.ResponseMapperUtils;
import io.quarkus.security.UnauthorizedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericUnauthenticatedExeptionMapper implements ExceptionMapper<UnauthorizedException> {

    @Override
    public Response toResponse(UnauthorizedException exception) {
        return ResponseMapperUtils.unauthorized();
    }
}
