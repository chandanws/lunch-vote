package io.philipg.lunchvote.service;

import io.philipg.lunchvote.model.Dish;
import io.philipg.lunchvote.util.exception.NotFoundException;

import java.util.List;

public interface DishService {
    Dish create(Dish dish);

    void delete(int id) throws NotFoundException;

    Dish get(int id) throws NotFoundException;

    List<Dish> getByName(String name) throws NotFoundException;

    void update(Dish dish);

    List<Dish> getAll();
}