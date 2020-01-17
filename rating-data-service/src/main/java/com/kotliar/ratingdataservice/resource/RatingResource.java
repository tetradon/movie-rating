package com.kotliar.ratingdataservice.resource;

import com.kotliar.ratingdataservice.model.Rating;
import com.kotliar.ratingdataservice.model.UserRating;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingResource {

  @RequestMapping("/{movieId}")
  public Rating getRating(@PathVariable("movieId") String movieId) {
    return new Rating(movieId, 4);
  }

  @RequestMapping("/users/{userId}")
  public UserRating getRatingForUser(@PathVariable("userId") String userId) {
    List<Rating> ratings = List.of(new Rating("100", 4), new Rating("101", 3));
    return new UserRating(ratings);
  }
}
