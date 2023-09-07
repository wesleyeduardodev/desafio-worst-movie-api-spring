package com.worstmovie.api.repository;
import com.worstmovie.api.model.Studio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudiosRepository extends CrudRepository<Studio, Long> {
    Optional<Studio> findByName(String name);
}
