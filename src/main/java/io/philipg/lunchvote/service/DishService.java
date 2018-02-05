package io.philipg.lunchvote.service;

import io.philipg.lunchvote.model.Dish;
import io.philipg.lunchvote.model.State;
import io.philipg.lunchvote.util.exception.NotFoundException;

import java.util.List;

public interface DishService {
    Dish create(Dish dish, int restaurantId);

    void delete(int id, int restaurantId) throws NotFoundException;

    Dish get(int id) throws NotFoundException;

    Dish getWithRestaurant(int id, int restaurantId) throws NotFoundException;

    List<Dish> getByName(String name) throws NotFoundException;

    void update(Dish dish, int restaurantId);

    List<Dish> getAll(int restaurantId);

    List<Dish> getAllByState(int restaurantId, State... states);
}