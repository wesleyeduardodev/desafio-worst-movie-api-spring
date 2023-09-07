package com.worstmovie.api.model;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import jakarta.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "worst_movie_producer")
public class WorstMovieProducer extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "id_worst_movie_producer_seq", sequenceName = "pk_id_worst_movie_producer", allocationSize = 0, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_worst_movie_producer_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_worst_movie", referencedColumnName = "id")
    private WorstMovie worstMovie;

    @ManyToOne
    @JoinColumn(name = "id_producer", referencedColumnName = "id")
    private Producer producer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorstMovieProducer that = (WorstMovieProducer) o;
        return worstMovie.equals(that.worstMovie) && producer.equals(that.producer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(worstMovie, producer);
    }
}
