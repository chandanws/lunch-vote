package io.philipg.lunchvote.repository;

import io.philipg.lunchvote.model.Restaurant;
import io.philipg.lunchvote.model.State;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save (Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();

    List<Restaurant> getAllByState(State state);
}