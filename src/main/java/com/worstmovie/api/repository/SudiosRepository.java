package com.worstmovie.api.repository;
import com.worstmovie.api.model.Studio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SudiosRepository implements PanacheRepository<Studio> {
}
