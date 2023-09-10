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
    public Response findProducerAwardsRange() {
        return Response.ok(awardsRangeService.findProducerAwardsRange(rankingService.findWorstMovieProducer())).build();
    }

    @Override
    @PermitAll
    public Response findStudiosAwardsRange() {
        return Response.ok(awardsRangeService.findStudioAwardsRange(rankingService.findWorstMovieStudio())).build();
    }
}
