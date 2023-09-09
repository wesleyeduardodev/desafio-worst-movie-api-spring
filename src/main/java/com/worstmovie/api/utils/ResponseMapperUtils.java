package com.worstmovie.api.utils;
import com.worstmovie.api.exceptions.ApiErrorDTO;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseMapperUtils {

    public static Response badRequest(String code, String message, String orientation) {
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(ApiErrorDTO
                        .builder()
                        .code(code)
                        .message(message)
                        .orientation(orientation)
                        .build())
                .build();
    }

    public static Response badRequest(String code, String message) {
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(ApiErrorDTO
                        .builder()
                        .code(code)
                        .message(message)
                        .build())
                .build();
    }

    public static Response internalError(String code, String message, String orientation) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(ApiErrorDTO
                        .builder()
                        .code(code)
                        .message(message)
                        .orientation(orientation)
                        .build())
                .build();
    }

    public static Response unavailable() {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
    }

    public static Response notFound() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public static Response noContent() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    public static Response unauthorized() {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    public static Response forbiden() {
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
