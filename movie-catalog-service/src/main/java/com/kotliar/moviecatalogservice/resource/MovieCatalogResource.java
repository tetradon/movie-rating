package com.kotliar.moviecatalogservice.resource;

import com.kotliar.moviecatalogservice.models.CatalogItem;
import com.kotliar.moviecatalogservice.models.Rating;
import com.kotliar.moviecatalogservice.models.UserRating;
import com.kotliar.moviecatalogservice.port.CatalogItemPort;
import com.kotliar.moviecatalogservice.port.UserRatingPort;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

  private CatalogItemPort catalogItemPort;
  private UserRatingPort userRatingPort;

  //private WebClient.Builder webClientBuilder;

  public MovieCatalogResource(
      CatalogItemPort catalogItemPort,
      UserRatingPort userRatingPort) {
    this.catalogItemPort = catalogItemPort;
    this.userRatingPort = userRatingPort;
  }

  @RequestMapping("/{userId}")
  public List<CatalogItem> getCatalog(@PathVariable String userId) {
    UserRating userRating = userRatingPort.getUserRating(userId);

    return userRating.getUserRatings().stream()
        .map(rating -> catalogItemPort.getCatalogItem(rating))
        .collect(Collectors.toList());
  }

}



/*      Movie movie = webClientBuilder.build()
          .get()
          .uri(requestUrl)
          .retrieve()
          .bodyToMono(Movie.class)
          .block();
*/
