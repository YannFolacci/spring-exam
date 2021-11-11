package com.itakademy.javi.controllers;

import com.itakademy.javi.models.Game;
import com.itakademy.javi.models.Review;
import com.itakademy.javi.repositories.GameRepository;
import com.itakademy.javi.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/javi/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("")
    public List<Game> getAllGames(){
        return (List<Game>) gameRepository.findAll();
    }
    @PostMapping("")
    public ResponseEntity<Game> createGame(@Valid @RequestBody Game game, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }

        gameRepository.save(game);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @GetMapping("/{game}")
    public Game getGameById(@PathVariable(name = "game", required = false) Game game){
        if(game == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        return game;
    }
    @PutMapping("/{game}")
    public ResponseEntity<Game> getGameById(@PathVariable(name = "game", required = false) Game game, @Valid @RequestBody Game gameUpdated, BindingResult bindingResult){
        if(game == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }
        gameUpdated.setId(game.getId());
        gameRepository.save(gameUpdated);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
    @DeleteMapping("/{game}")
    public ResponseEntity<Game> deleteGame(@PathVariable(name = "game", required = false) Game game){
        if(game == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{game}/reviews")
    public List<Review> getReview(@PathVariable(name = "game", required = false) Game game){
        if(game == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        return reviewRepository.findAllByGame(game);
    }
    @GetMapping("/{game}/last_review")
    public Review getLastReview(@PathVariable(name = "game", required = false) Game game){
        if(game == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        return reviewRepository.findTopByGameOrderBySendDateDesc(game);
    }
}
