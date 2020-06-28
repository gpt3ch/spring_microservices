package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/movies")
public class MovieInfoResource {

    @RequestMapping("/{movieid}")
    public Movie getMovieInfo(@PathVariable("movieid") String movieId){
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        return new Movie(movieId, "tdk rises "+randomNum);
    }
}
