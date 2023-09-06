package com.worstmovie.api.model;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "worst_movie")
@Entity
public class WorstMovie extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="yea", nullable = false)
    private Integer year;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="winner")
    private boolean winner;
}
