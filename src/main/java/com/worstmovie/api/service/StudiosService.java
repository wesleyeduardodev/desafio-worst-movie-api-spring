package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.StudioResponseDTO;
import com.worstmovie.api.model.Studio;
import com.worstmovie.api.utils.CSVMovieListUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class StudiosService {

    public List<Studio> findAllStudios() {
        return Studio.listAll();
    }

    @Transactional
    public void saveStudios(String name) {
        Studio.persist(Studio.builder()
                .name(name)
                .build());
    }

    @Transactional
    public void saveAllStudios(List<Studio> studios) {
        Studio.persist(studios);
    }

    public List<StudioResponseDTO> studiosEntityToStudiosResponseDTO(List<Studio> studios) {
        List<StudioResponseDTO> studioResponseDTOS = new ArrayList<>();
        studios.forEach(studio -> studioResponseDTOS.add(StudioResponseDTO.builder()
                .id(studio.getId())
                .name(studio.getName())
                .build()));
        return studioResponseDTOS;
    }

    public List<Studio> returnStudiosFromCSVRecord(CSVRecord studioRecord) {
        return CSVMovieListUtils.splitAndCleanRecords(studioRecord, CSVMovieListUtils.HEADER_STUDIOS).stream()
                .map(this::buildStudios)
                .collect(Collectors.toList());
    }

    private Studio buildStudios(String record) {
        return Studio.builder()
                .name((record))
                .build();
    }
}
