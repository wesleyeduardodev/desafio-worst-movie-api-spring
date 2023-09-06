package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.WorstMovieResponseDTO;
import com.worstmovie.api.model.WorstMovie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
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
}
