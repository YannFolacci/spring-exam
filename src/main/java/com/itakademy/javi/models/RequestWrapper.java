package com.itakademy.javi.models;

public class RequestWrapper {
    String gameName;
    Review review;

    public RequestWrapper() {
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
