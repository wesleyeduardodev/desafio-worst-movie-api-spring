package com.worstmovie.api.service;
import com.worstmovie.api.dto.LinkDTO;
import com.worstmovie.api.dto.request.WorstMovieRequestDTO;
import com.worstmovie.api.dto.response.WorstMovieResponseDTO;
import com.worstmovie.api.model.WorstMovie;
import com.worstmovie.api.repository.WorstMovieRepository;
import com.worstmovie.api.utils.CSVMovieListUtils;
import com.worstmovie.api.utils.LinksUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.csv.CSVRecord;
import java.util.ArrayList;
import java.util.Collections;
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

    public WorstMovie saveWorstMovie(String year, String title, boolean winner) {
        return worstMovieRepository.save(WorstMovie
                .builder()
                .year(year)
                .title(title)
                .winner(winner)
                .build());
    }

    public void deleteWorstMovie(Long id) {
        WorstMovie.deleteById(id);
    }

    public void updateWorstMovie(Long id, WorstMovieRequestDTO worstMovieRequestDTO) {
        WorstMovie worstMovie = WorstMovie.findById(id);
        worstMovie.setYear(worstMovieRequestDTO.getYear());
        worstMovie.setTitle(worstMovieRequestDTO.getTitle());
        worstMovie.setWinner(worstMovieRequestDTO.getWinner());
        worstMovieRepository.save(worstMovie);
    }

    public List<WorstMovieResponseDTO> toWorstMoviesResponseDTO(List<WorstMovie> worstMovies, String path) {
        List<WorstMovieResponseDTO> worstMovieResponseDTOS = new ArrayList<>();
        worstMovies.forEach(worstMovie -> worstMovieResponseDTOS.add(WorstMovieResponseDTO.builder()
                .id(worstMovie.getId())
                .year(worstMovie.getYear())
                .title(worstMovie.getTitle())
                .winner(worstMovie.isWinner())
                .links(Collections.singletonList(LinkDTO.builder().rel("rel").href(path + "/" + worstMovie.getId()).build()))
                .build()));
        return worstMovieResponseDTOS;
    }

    public WorstMovieResponseDTO toWorstMovieResponseDTO(WorstMovie worstMovie, String path) {
        return WorstMovieResponseDTO
                .builder()
                .id(worstMovie.getId())
                .year(worstMovie.getYear())
                .title(worstMovie.getTitle())
                .links(LinksUtils.generateLinks(path, null))
                .build();
    }

    public WorstMovie returnWorstMovieFromCSVRecord(CSVRecord worstMovieCsvRecord) {
        return WorstMovie.builder()
                .year(worstMovieCsvRecord.get(CSVMovieListUtils.HEADER_YEAR))
                .title(worstMovieCsvRecord.get(CSVMovieListUtils.HEADER_TITLE))
                .winner(worstMovieCsvRecord.get(CSVMovieListUtils.HEADER_WINNER).equals(CSVMovieListUtils.WINNER) ? Boolean.TRUE : Boolean.FALSE)
                .build();
    }
}
