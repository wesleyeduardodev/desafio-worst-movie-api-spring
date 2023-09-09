package com.worstmovie.api.cache;
import com.worstmovie.api.model.Producer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreProducers {

    @Bean
    public CacheStore<Producer> storeProducers() {
        return new CacheStore<>(60, TimeUnit.SECONDS);
    }
}
