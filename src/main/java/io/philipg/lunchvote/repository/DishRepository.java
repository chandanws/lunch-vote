package io.philipg.lunchvote.repository;

import io.philipg.lunchvote.model.Dish;

import java.util.List;

public interface DishRepository {

    Dish save (Dish dish);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    // null if not found
    List<Dish> getByName(String name);

    List<Dish> getAll();
}
