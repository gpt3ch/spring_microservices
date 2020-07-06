package com.example.moviecatalogservice.services;

import com.example.moviecatalogservice.models.Rating;
import com.example.moviecatalogservice.models.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserMovieRatings {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating",
    commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")})
    public UserRatings getUserRating(@PathVariable("usr_id") String userId) {
        return restTemplate.getForObject("http://movie-rating-service/ratingsdata/users/" + userId, UserRatings.class);
    }

    public UserRatings getFallbackUserRating(@PathVariable("usr_id") String userId) {
        return new UserRatings(Arrays.asList(
                new Rating("Movie Id not found", null),
                new Rating("Movie Id not found", null)
        ));
    }

}
