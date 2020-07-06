Spring micro-services basics 1

Consists of 3 Eureka microservice clients movie-catalog-service, movie-rating-service, movie-info-service and 
1 Eureka service discovery server discovery-server. The movie-catalog-service calls the other 2 services using
RestTemplate or WebClient.

Implemented timeouts, Circuit breaker pattern and bulkhead pattern using Hystrix.

Added dynamic data calls from TheMovieDB API.


Spring micro-services basics 2

Consists of REST implementations of get/post/put/delete requests, request validations and exception handling.