package com.example.moviecatalogservice.resources;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;
import com.example.moviecatalogservice.models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/{usr_id}")
    public List<CatalogItem> getCatalogItem(@PathVariable("usr_id") String userId){
        UserRatings usr = restTemplate.getForObject("http://movie-rating-service/ratingsdata/users/" +userId, UserRatings.class);
        List<Rating> ratings = usr.getUserRatings();
        List<String> instancesList = (List<String>) clientList.stream().collect(Collectors.toList());
        return ratings.stream().map(
                rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
                    return new CatalogItem(
                            movie.getName(),
                            "response using rest template",
                            rating.getRating()
                    );
                }
        ).collect(Collectors.toList());
    }

    @RequestMapping("/webclient/{usr_id}")
    public List<CatalogItem> getCatalogItemWebClient(@PathVariable ("usr_id") String usr_id){
        
        UserRatings usr = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/ratingsdata/users/" +usr_id)
                .retrieve()
                .bodyToMono(UserRatings.class)
                .block();
        List<Rating> ratings = usr.getUserRatings();

        return ratings.stream()
                .map(rating -> {
                    Movie movie = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8082/movies/"+rating.getMovieId())
                            .retrieve()
                            .bodyToMono(Movie.class)
                            .block();
                    return new CatalogItem(movie.getName(), "response using webclient", rating.getRating());
                }).collect(Collectors.toList());
    }


}
