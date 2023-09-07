package com.worstmovie.api.repository;
import com.worstmovie.api.dto.response.WorstMovieProducerDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class AwardsRangeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<WorstMovieProducerDTO> findWorstMovieProducerDTO() {
        String nativeQuery = "select p.name, wm.yea\n" +
                "from worst_movie_producer wmp\n" +
                "         inner join producer p on p.id = wmp.id_producer\n" +
                "         inner join worst_movie wm on wm.id = wmp.id_worst_movie\n" +
                "where wm.winner is true\n" +
                "group by p.name, wm.yea\n" +
                "order by p.name";
        Query query = entityManager.createNativeQuery(nativeQuery);
        List<Object[]> queryResultList = query.getResultList();
        List<WorstMovieProducerDTO> worstMovieProducerDTOS = new ArrayList<>();
        for (Object[] result : queryResultList) {
            worstMovieProducerDTOS.add(WorstMovieProducerDTO
                    .builder()
                    .name((String) result[0])
                    .year((Integer) result[1])
                    .build());
        }
        return worstMovieProducerDTOS;
    }
}