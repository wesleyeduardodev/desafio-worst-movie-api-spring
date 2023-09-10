package com.worstmovie.api.resources;
import com.worstmovie.api.service.AwardsRangeService;;
import com.worstmovie.api.service.RankingService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

public class AwardsRangeResource implements AwardsRangeResourceAPI {

    @Inject
    AwardsRangeService awardsRangeService;

    @Inject
    RankingService rankingService;

    @Override
    @PermitAll
    public Response findAwardsRangeProducer() {
        return Response.ok(awardsRangeService.findAwardsRangeProducer(rankingService.findWorstMovieProducer())).build();
    }

    @Override
    @PermitAll
    public Response findAwardsRangeStudios() {
        return Response.ok(awardsRangeService.findAwardsRangeStudio(rankingService.findWorstMovieStudio())).build();
    }
}
