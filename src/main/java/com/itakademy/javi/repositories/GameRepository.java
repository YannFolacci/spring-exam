package com.itakademy.javi.repositories;

import com.itakademy.javi.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    public Game findByName(String name);
}
