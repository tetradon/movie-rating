package com.kotliar.moviecatalogservice.port;

import com.kotliar.moviecatalogservice.models.CatalogItem;
import com.kotliar.moviecatalogservice.models.Movie;
import com.kotliar.moviecatalogservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatalogItemPort {

  private RestTemplate restTemplate;

  public CatalogItemPort(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
  public CatalogItem getCatalogItem(Rating rating) {
    String movieApi = "http://movie-info-service/movies/" + rating.getMovieId();
    Movie movie = restTemplate.getForObject(movieApi, Movie.class);
    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
  }

  private CatalogItem getFallbackCatalogItem(Rating rating) {
    return new CatalogItem("Movie not found", "", rating.getRating());
  }
}
