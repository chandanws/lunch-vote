package io.philipg.lunchvote.repository.datajpa;

import io.philipg.lunchvote.model.Dish;
import io.philipg.lunchvote.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaDishRepositoryImpl implements DishRepository{
    private static final Sort SORT_NAME_NAME = new Sort(Sort.Direction.ASC, "name", "name");

    @Autowired
    private CrudDishRepository crudRepository;

    @Override
    public Dish save(Dish dish) {
        return crudRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) !=0;
    }

    @Override
    public Dish get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getByName(String name) {
        return crudRepository.findByNameContaining(name);
    }

    @Override
    public List<Dish> getAll() {
        return crudRepository.findAll(SORT_NAME_NAME);
    }
}