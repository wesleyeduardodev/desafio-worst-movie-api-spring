package com.worstmovie.api.model;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "worst_movie")
public class WorstMovie extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "id_worst_movie_seq", sequenceName = "pk_id_worst_movie", allocationSize = 0, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_worst_movie_seq")
    private Long id;

    @Column(name = "yea", nullable = false)
    private Integer year;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "winner")
    private boolean winner;

    @OneToMany(mappedBy = "worstMovie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorstMovieProducer> worstMovieProducers;

    @OneToMany(mappedBy = "worstMovie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorstMovieStudios> worstMovieStudio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorstMovie that = (WorstMovie) o;
        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
