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
    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Dish get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Dish> getByName(String name) throws NotFoundException {
        Assert.notNull(name, "name must not be null");
        return checkNotFound(repository.getByName(name), "name=" + name);
    }

    @Override
    public void update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(repository.save(dish), dish.getId());
    }

    @Override
    public List<Dish> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Dish> getAllByState(State... states) {
        return repository.getAllByState(Arrays.asList(states));
    }
}