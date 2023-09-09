package com.worstmovie.api.exceptionsmappers;
import com.worstmovie.api.exceptions.UnavailableException;
import com.worstmovie.api.utils.ResponseMapperUtils;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UnavailableExceptionMapper implements ExceptionMapper<UnavailableException> {

    @Override
    public Response toResponse(UnavailableException exception) {
        return ResponseMapperUtils.unavailable();
    }
}
