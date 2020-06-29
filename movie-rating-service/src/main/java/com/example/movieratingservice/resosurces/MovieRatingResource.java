package com.example.movieratingservice.resosurces;

import com.example.movieratingservice.models.Rating;
import com.example.movieratingservice.models.UserRatings;
import com.example.movieratingservice.models.moviedb.TMDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class MovieRatingResource {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    @RequestMapping("/{movieId}")
    public Rating getRatings(@PathVariable("movieId") String movieId) {
        TMDBResponse tmdbResponse = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey, TMDBResponse.class
        );
        System.out.println("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey);
        return new Rating(movieId, tmdbResponse.getVoteAverage());
    }

    @RequestMapping("/users/{userId}")
    public UserRatings getUserRatings(@PathVariable("userId") String movieId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("500", 8.0),
                new Rating("600", 5.0),
                new Rating("700", 9.0)
        );

        return new UserRatings(ratings);
    }
}
