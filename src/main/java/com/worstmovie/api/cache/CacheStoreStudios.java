package com.worstmovie.api.cache;
import com.worstmovie.api.model.Studio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreStudios {

    @Bean
    public CacheStore<Studio> storeStudios() {
        return new CacheStore<>(60, TimeUnit.SECONDS);
    }
}
