package com.kotliar.moviecatalogservice.port;

import com.kotliar.moviecatalogservice.models.Rating;
import com.kotliar.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Collections;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRatingPort {

  private RestTemplate restTemplate;

  public UserRatingPort(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @HystrixCommand(fallbackMethod = "getFallbackUserRating")
  public UserRating getUserRating(String userId) {
    String ratingsUri = "http://rating-data-service/ratings/users/" + userId;
    return restTemplate.getForObject(ratingsUri, UserRating.class);
  }

  private UserRating getFallbackUserRating(String userId) {
    UserRating userRating = new UserRating();
    userRating.setUserId(userId);
    userRating.setUserRatings(Collections.singletonList(
        new Rating("0", 0)
    ));
    return userRating;
  }
}
