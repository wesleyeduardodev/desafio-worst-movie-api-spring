package com.worstmovie.api.repository;
import com.worstmovie.api.dto.response.WorstMovieProducerDTO;
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
public class AwardsRangeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<WorstMovieProducerDTO> findWorstMovieProducerDTO() {
        String nativeQueryWorstMovieProducer = "select p.name, string_agg(wm.yea, ',') as concatenatedAwardRange\n" +
                "from worst_movie_producer wmp\n" +
                "         inner join producer p on p.id = wmp.id_producer\n" +
                "         inner join worst_movie wm on wm.id = wmp.id_worst_movie\n" +
                "where wm.winner is true\n" +
                "group by p.name\n" +
                "order by p.name";
        Query query = entityManager.createNativeQuery(nativeQueryWorstMovieProducer);
        List<Object[]> queryResultList = query.getResultList();
        List<WorstMovieProducerDTO> worstMovieProducerDTOS = new ArrayList<>();
        for (Object[] result : queryResultList) {
            String concatenatedYears = (String) result[1];
            List<Integer> years = ConverterUtils.convertConcatenatedNumbersIntoListOfIntegersBySeparator(concatenatedYears, ",");
            worstMovieProducerDTOS.add(WorstMovieProducerDTO
                    .builder()
                    .name((String) result[0])
                    .years(years.stream().sorted(Comparator.comparing(i -> i)).collect(Collectors.toList()))
                    .build());
        }
        return worstMovieProducerDTOS;
    }
}