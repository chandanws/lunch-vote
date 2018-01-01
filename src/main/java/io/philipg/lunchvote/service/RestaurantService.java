package io.philipg.lunchvote.service;

import io.philipg.lunchvote.model.Restaurant;
import io.philipg.lunchvote.util.exception.NotFoundException;

import java.util.List;

public interface RestaurantService {

    Restaurant create (Restaurant restaurant);

    void delete(int id) throws NotFoundException;

    Restaurant get(int id) throws NotFoundException;

    void update(Restaurant restaurant);

    List<Restaurant> getAll();
}