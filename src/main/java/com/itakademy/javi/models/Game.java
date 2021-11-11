package com.itakademy.javi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    private String name;

    @Column(name = "description")
    @NotNull(message = "description can not be null")
    @NotBlank(message = "description can not be blank")
    private String description;

    @Column(name = "release_date")
    @NotNull(message = "release date can not be null")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Review> reviews;

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public Game setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Game setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Game setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Game setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Game setReviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }
}
