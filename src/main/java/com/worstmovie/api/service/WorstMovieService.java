package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.WorstMovieResponseDTO;
import com.worstmovie.api.model.WorstMovie;
import com.worstmovie.api.repository.WorstMovieRepository;
import com.worstmovie.api.utils.CSVMovieListUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.csv.CSVRecord;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class WorstMovieService {

    @Inject
    WorstMovieRepository worstMovieRepository;

    public List<WorstMovie> findAllWorstMovies() {
        return WorstMovie.listAll();
    }

    public WorstMovie saveWorstMovie(WorstMovie worstMovie) {
        return worstMovieRepository.save(worstMovie);
    }

    public List<WorstMovieResponseDTO> worstMoviesEntityToWorstMovieResponseDTO(List<WorstMovie> worstMovies) {
        List<WorstMovieResponseDTO> worstMovieResponseDTOS = new ArrayList<>();
        worstMovies.forEach(worstMovie -> worstMovieResponseDTOS.add(WorstMovieResponseDTO.builder()
                .id(worstMovie.getId())
                .year(Integer.valueOf(worstMovie.getYear()))
                .title(worstMovie.getTitle())
                .winner(worstMovie.isWinner())
                .build()));
        return worstMovieResponseDTOS;
    }

    public WorstMovie returnWorstMovieFromCSVRecord(CSVRecord worstMovieCsvRecord) {
        return WorstMovie.builder()
                .year(worstMovieCsvRecord.get(CSVMovieListUtils.HEADER_YEAR))
                .title(worstMovieCsvRecord.get(CSVMovieListUtils.HEADER_TITLE))
                .winner(worstMovieCsvRecord.get(CSVMovieListUtils.HEADER_WINNER).equals(CSVMovieListUtils.WINNER) ? Boolean.TRUE : Boolean.FALSE)
                .build();
    }
}
