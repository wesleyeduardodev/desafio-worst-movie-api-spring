package com.worstmovie.api.exceptionsmappers;
import com.worstmovie.api.exceptions.UnauthenticatedExeption;
import com.worstmovie.api.utils.ResponseMapperUtils;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UnauthenticatedExeptionMapper implements ExceptionMapper<UnauthenticatedExeption> {

    @Override
    public Response toResponse(UnauthenticatedExeption exception) {
        return ResponseMapperUtils.unauthorized();
    }
}
