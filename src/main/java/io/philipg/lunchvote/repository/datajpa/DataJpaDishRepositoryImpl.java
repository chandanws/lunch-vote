package io.philipg.lunchvote.repository.datajpa;

import io.philipg.lunchvote.model.Dish;
import io.philipg.lunchvote.model.State;
import io.philipg.lunchvote.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaDishRepositoryImpl implements DishRepository{

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId()) == null){
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudDishRepository.delete(id, restaurantId) !=0;
    }

    @Override
    public Dish get(int id) {
        return crudDishRepository.findById(id).orElse(null);
    }

    @Override
    public Dish getWithRestaurant(int id, int restaurantId) {
        return crudDishRepository.getWithRestaurant(id, restaurantId);
    }

    @Override
    public List<Dish> getByName(String name) {
        return crudDishRepository.findByNameContaining(name);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return crudDishRepository.getAll(restaurantId);
    }

    @Transactional
    @Override
    public List<Dish> getAllByState(int restaurantId, Iterable<State> states) {
        return crudDishRepository.getAllByStateIn(restaurantId, states);
    }
}