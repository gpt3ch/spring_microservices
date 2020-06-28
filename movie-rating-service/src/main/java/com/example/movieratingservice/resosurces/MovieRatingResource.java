package com.example.movieratingservice.resosurces;

import com.example.movieratingservice.models.Rating;
import com.example.movieratingservice.models.UserRatings;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class MovieRatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRatings(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 9);
    }

    @RequestMapping("/users/{userId}")
    public UserRatings getUserRatings(@PathVariable("userId") String movieId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("movieWeb123", 600),
                new Rating("movieWeb123", 700),
                new Rating("movieWeb456", 800)
        );

        return new UserRatings(ratings);
    }
}
