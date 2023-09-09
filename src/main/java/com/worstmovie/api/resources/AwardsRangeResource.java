package com.worstmovie.api.resources;
import com.worstmovie.api.service.AwardsRangeService;;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class AwardsRangeResource implements AwardsRangeResourceAPI {

    @Inject
    AwardsRangeService awardsRangeService;

    @Override
    public Response findAwardsRangeProducer() {
        return Response.ok(awardsRangeService.findAwardsRangeProducer()).build();
    }

    @Override
    public Response findAwardsRangeStudios() {
        return Response.ok(awardsRangeService.findAwardsRangeStudio()).build();
    }
}
