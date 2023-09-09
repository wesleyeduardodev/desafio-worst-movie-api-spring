package com.worstmovie.api.exceptionsmappers;
import com.worstmovie.api.exceptions.EnvironmentException;
import jakarta.ws.rs.core.Response;
import com.worstmovie.api.utils.ResponseMapperUtils;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EnvironmentExceptionMapper implements ExceptionMapper<EnvironmentException> {

    @Override
    public Response toResponse(EnvironmentException exception) {
        return ResponseMapperUtils.internalError(exception.getCode(), exception.getMessage(), exception.getOrientation());
    }
}
