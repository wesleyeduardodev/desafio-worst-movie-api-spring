package com.worstmovie.api.repository;
import com.worstmovie.api.dto.response.RankingDTO;
import com.worstmovie.api.utils.ConverterUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RankingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<RankingDTO> findWorstMovieProducerDTO() {
        String nativeQueryWorstMovieProducer = "select p.name, string_agg(wm.yea, ',') as concatenatedAwardRange\n" +
                "from worst_movie_producer wmp\n" +
                "         inner join producer p on p.id = wmp.id_producer\n" +
                "         inner join worst_movie wm on wm.id = wmp.id_worst_movie\n" +
                "where wm.winner is true\n" +
                "group by p.name\n" +
                "order by p.name";
        Query query = entityManager.createNativeQuery(nativeQueryWorstMovieProducer);
        List<RankingDTO> rankingProducerDTOS = buildRankings(query.getResultList());
        return rankingProducerDTOS;
    }

    public List<RankingDTO> findWorstMovieStudioDTO() {
        String nativeQueryWorstMovieStudio = "select s.name, string_agg(wm.yea, ',') as concatenatedAwardRange\n" +
                "from worst_movie_studio wms\n" +
                "         inner join studio s on s.id = wms.id_studio\n" +
                "         inner join worst_movie wm on wm.id = wms.id_worst_movie\n" +
                "where wm.winner is true\n" +
                "group by s.name\n" +
                "order by s.name";
        Query query = entityManager.createNativeQuery(nativeQueryWorstMovieStudio);
        List<RankingDTO> rankingStudiosDTOS = buildRankings(query.getResultList());
        return rankingStudiosDTOS;
    }

    private List<RankingDTO> buildRankings(List<Object[]> queryResultList) {
        List<RankingDTO> rankingDTOS = new ArrayList<>();
        for (Object[] result : queryResultList) {
            String concatenatedYears = (String) result[1];
            List<Integer> years = ConverterUtils.convertConcatenatedNumbersIntoListOfIntegersBySeparator(concatenatedYears, ",");
            rankingDTOS.add(RankingDTO
                    .builder()
                    .name((String) result[0])
                    .years(years.stream().sorted(Comparator.comparing(i -> i)).collect(Collectors.toList()))
                    .build());
        }
        return rankingDTOS;
    }
}