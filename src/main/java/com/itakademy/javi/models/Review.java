package com.itakademy.javi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "send_date")
    @NotNull(message = "note can not be null")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sendDate;

    @Column(name = "note")
    @NotNull(message = "note can not be null")
    @Min(value = 0,message = "note can not be less than 0")
    private int note;

    @Column(name = "description")
    @NotNull(message = "description can not be null")
    @NotBlank(message = "description can not be blank")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Game game;

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public Review setId(Long id) {
        this.id = id;
        return this;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public Review setSendDate(Date sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    public int getNote() {
        return note;
    }

    public Review setNote(int note) {
        this.note = note;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Review setDescription(String description) {
        this.description = description;
        return this;
    }

    public Game getGame() {
        return game;
    }

    public Review setGame(Game game) {
        this.game = game;
        return this;
    }
}
