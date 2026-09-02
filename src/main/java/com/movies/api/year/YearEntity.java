package com.movies.api.year;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movies.api.movie.MovieEntity;

@Entity
@Table(name = "years")
public class YearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_year")
    private Long id;

    @Column(name = "year_value")
    private Integer value;

    @JsonIgnore
    @OneToMany(mappedBy = "year")
    private Set<MovieEntity> movies;

    public YearEntity() {
    }

    public YearEntity(Long id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
