package io.philipg.lunchvote.repository;

import io.philipg.lunchvote.model.Dish;
import io.philipg.lunchvote.model.State;

import java.util.List;

public interface DishRepository {

    Dish save (Dish dish, int restaurantId);

    // false if not found
    boolean delete(int id, int restaurantId);

    // null if not found
    Dish get(int id);

    Dish getWithRestaurant(int id, int restaurantId);

    // null if not found
    List<Dish> getByName(String name);

    List<Dish> getAll(int restaurantId);

    List<Dish> getAllByState(int restaurantId, Iterable<State> states);
}
