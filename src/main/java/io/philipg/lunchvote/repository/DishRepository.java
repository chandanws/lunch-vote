package io.philipg.lunchvote.repository;

import io.philipg.lunchvote.model.Dish;
import io.philipg.lunchvote.model.Restaurant;

import java.util.List;

public interface DishRepository {

    Dish save (Dish dish);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    List<Dish> getAll(int menuId);
}
