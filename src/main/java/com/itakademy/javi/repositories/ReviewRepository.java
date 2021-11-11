package com.itakademy.javi.repositories;

import com.itakademy.javi.models.Game;
import com.itakademy.javi.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    public List<Review> findAllByGame(Game game);
    public Review findTopByGameOrderBySendDateDesc(Game game);
}
