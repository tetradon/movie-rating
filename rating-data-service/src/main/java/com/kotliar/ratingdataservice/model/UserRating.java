package com.kotliar.ratingdataservice.model;

import java.util.List;

public class UserRating {

  public UserRating() {
  }

  public UserRating(List<Rating> userRatings) {
    this.userRatings = userRatings;
  }

  private List<Rating> userRatings;

  public List<Rating> getUserRatings() {
    return userRatings;
  }

  public void setUserRatings(List<Rating> userRatings) {
    this.userRatings = userRatings;
  }
}
