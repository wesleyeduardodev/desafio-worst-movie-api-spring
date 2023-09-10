package com.worstmovie.api.resources;
import com.worstmovie.api.service.AwardsRangeService;;
import com.worstmovie.api.service.RankingService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class AwardsRangeResource implements AwardsRangeResourceAPI {

    @Inject
    AwardsRangeService awardsRangeService;

    @Inject
    RankingService rankingService;

    @Override
    @RolesAllowed("admin")
    public Response findAwardsRangeProducer() {
        return Response.ok(awardsRangeService.findAwardsRangeProducer(rankingService.findWorstMovieProducer())).build();
    }

    @Override
    @RolesAllowed("admin")
    public Response findAwardsRangeStudios() {
        return Response.ok(awardsRangeService.findAwardsRangeStudio(rankingService.findWorstMovieStudio())).build();
    }
}
