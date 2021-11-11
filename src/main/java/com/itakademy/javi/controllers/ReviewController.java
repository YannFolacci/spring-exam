package com.itakademy.javi.controllers;

import com.itakademy.javi.models.Game;
import com.itakademy.javi.models.RequestWrapper;
import com.itakademy.javi.models.Review;
import com.itakademy.javi.repositories.GameRepository;
import com.itakademy.javi.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/javi/reviews")
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    GameRepository gameRepository;

    @GetMapping("")
    public List<Review> getAllReviews(){
        return (List<Review>) reviewRepository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Review> createReview(@Valid @RequestBody RequestWrapper requestWrapper){
        if(requestWrapper == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "requestWrapper not found");
        }

        Game game = gameRepository.findByName(requestWrapper.getGameName());
        Review review = requestWrapper.getReview();
        review.setGame(game);
        reviewRepository.save(review);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/{review}")
    public Review getOneReview(@PathVariable(name = "review", required = false) Review review){
        if(review == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        return review;
    }

    @PutMapping("/{review}")
    public ResponseEntity<Review> modifyReview(@PathVariable(name = "review", required = false) Review review, @Valid @RequestBody Review reviewUpdated, BindingResult bindingResult){
        if(review == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }

        reviewUpdated.setId(review.getId());
        reviewRepository.save(reviewUpdated);
        return new ResponseEntity<>(reviewUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{review}")
    public ResponseEntity<Review> deleteReview(@PathVariable(name = "review", required = false) Review review){
        if(review == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
        review.getGame().getReviews().remove(review);
        gameRepository.save( review.getGame());
        reviewRepository.delete(review);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
