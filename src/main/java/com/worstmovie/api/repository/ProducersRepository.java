package com.worstmovie.api.repository;
import com.worstmovie.api.model.Producer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProducersRepository implements PanacheRepository<Producer> {
}
