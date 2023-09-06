package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.WorstMovieResponseDTO;
import com.worstmovie.api.model.WorstMovie;
import com.worstmovie.api.utils.CSVMovieListUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVRecord;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class WorstMovieService {

    public List<WorstMovie> findAllWorstMovies() {
        return WorstMovie.listAll();
    }

    @Transactional
    public void saveWorstMovie(Integer year, String title, boolean winner) {
        WorstMovie.persist(WorstMovie.builder()
                .year(year)
                .title(title)
                .winner(winner)
                .build());
    }

    @Transactional
    public void saveAllWorstMovie(List<WorstMovie> worstMovies) {
        WorstMovie.persist(worstMovies);
    }

    public List<WorstMovieResponseDTO> worstMoviesEntityToWorstMovieResponseDTO(List<WorstMovie> worstMovies) {
        List<WorstMovieResponseDTO> worstMovieResponseDTOS = new ArrayList<>();
        worstMovies.forEach(worstMovie -> worstMovieResponseDTOS.add(WorstMovieResponseDTO.builder()
                .id(worstMovie.getId())
                .year(worstMovie.getYear())
                .title(worstMovie.getTitle())
                .winner(worstMovie.isWinner())
                .build()));
        return worstMovieResponseDTOS;
    }

    public WorstMovie returnWorstMovieFromCSVRecord(CSVRecord worstMovieCsvRecord) {
        return WorstMovie.builder()
                .year(Integer.parseInt(worstMovieCsvRecord.get(CSVMovieListUtils.HEADER_YEAR)))
                .title(worstMovieCsvRecord.get(CSVMovieListUtils.HEADER_TITLE))
                .winner(worstMovieCsvRecord.get(CSVMovieListUtils.HEADER_WINNER).equals(CSVMovieListUtils.WINNER) ? Boolean.TRUE : Boolean.FALSE)
                .build();
    }
}
