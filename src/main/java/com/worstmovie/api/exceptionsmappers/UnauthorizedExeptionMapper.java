package com.worstmovie.api.exceptionsmappers;
import com.worstmovie.api.exceptions.UnauthorizedExeption;
import com.worstmovie.api.utils.ResponseMapperUtils;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UnauthorizedExeptionMapper implements ExceptionMapper<UnauthorizedExeption> {

    @Override
    public Response toResponse(UnauthorizedExeption exception) {
        return ResponseMapperUtils.forbiden();
    }
}
