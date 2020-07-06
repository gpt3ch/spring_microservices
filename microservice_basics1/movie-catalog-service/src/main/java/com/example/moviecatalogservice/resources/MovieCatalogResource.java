package com.example.moviecatalogservice.resources;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;
import com.example.moviecatalogservice.models.UserRatings;
import com.example.moviecatalogservice.services.UserCatalogItems;
import com.example.moviecatalogservice.services.UserMovieRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private WebClient.Builder webClientBuilder;


    @Autowired
    UserMovieRatings userMovieRatings;

    @Autowired
    UserCatalogItems userCatalogItems;

    @RequestMapping("/{usr_id}")
    public List<CatalogItem> getCatalog(@PathVariable("usr_id") String userId) {
        UserRatings usr = userMovieRatings.getUserRating(userId);
        List<Rating> ratings = usr.getUserRatings();
        return ratings.stream().map(rating ->
                userCatalogItems.getCatalogItem(rating)).collect(Collectors.toList()
        );
    }


    @RequestMapping("/webclient/{usr_id}")
    public List<CatalogItem> getCatalogItemWebClient(@PathVariable("usr_id") String usr_id) {

        UserRatings usr = webClientBuilder.build()
                .get()
                .uri("http://movie-rating-service/ratingsdata/users/" + usr_id)
                .retrieve()
                .bodyToMono(UserRatings.class)
                .block();
        List<Rating> ratings = usr.getUserRatings();

        return ratings.stream()
                .map(rating -> {
                    Movie movie = webClientBuilder.build()
                            .get()
                            .uri("http://movie-info-service/movies/" + rating.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();
                    return new CatalogItem(movie.getName(), "response using webclient", rating.getRating());
                }).collect(Collectors.toList());
    }


}
