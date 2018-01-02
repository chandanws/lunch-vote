package io.philipg.lunchvote.service;

import io.philipg.lunchvote.model.Dish;
import io.philipg.lunchvote.model.State;
import io.philipg.lunchvote.repository.DishRepository;
import io.philipg.lunchvote.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

import static io.philipg.lunchvote.util.ValidationUtil.checkNotFound;
import static io.philipg.lunchvote.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, restaurantId);
    }

    @Override
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @Override
    public Dish get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @Override
    public Dish getWithRestaurant(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.getWithRestaurant(id, restaurantId), id);
    }

    @Override
    public List<Dish> getByName(String name) throws NotFoundException {
        Assert.notNull(name, "name must not be null");
        return checkNotFound(repository.getByName(name), "name=" + name);
    }

    @Override
    public void update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Override
    public List<Dish> getAllByState(int restaurantId, State... states) {
        return repository.getAllByState(restaurantId, Arrays.asList(states));
    }
}