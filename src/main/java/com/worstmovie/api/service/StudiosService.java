package com.worstmovie.api.service;
import com.worstmovie.api.dto.response.StudioResponseDTO;
import com.worstmovie.api.model.Studio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    public List<StudioResponseDTO> studiosEntityToStudiosResponseDTO(List<Studio> studios) {
        List<StudioResponseDTO> studioResponseDTOS = new ArrayList<>();
        studios.forEach(studio -> studioResponseDTOS.add(StudioResponseDTO.builder()
                .id(studio.getId())
                .name(studio.getName())
                .build()));
        return studioResponseDTOS;
    }
}
