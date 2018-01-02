package io.philipg.lunchvote.service;

import io.philipg.lunchvote.RestaurantTestData;
import io.philipg.lunchvote.UserTestData;
import io.philipg.lunchvote.model.Dish;
import io.philipg.lunchvote.model.State;
import io.philipg.lunchvote.repository.JpaUtil;
import io.philipg.lunchvote.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.util.List;

import static io.philipg.lunchvote.DishTestData.*;
import static io.philipg.lunchvote.RestaurantTestData.RESTAURANT1;
import static io.philipg.lunchvote.RestaurantTestData.RESTAURANT1_ID;
import static io.philipg.lunchvote.RestaurantTestData.RESTAURANT3_ID;

public class DishServiceTest extends AbstractServiceTest {
    @Autowired
    protected DishService service;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    protected JpaUtil jpaUtil;

    @Before
    public void setUp() {
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void create(){
        Dish created = getCreated();
        service.create(created, RESTAURANT1_ID);
        assertMatch(service.getAllByState(RESTAURANT1_ID, State.STATE_ACTIVE), DISH1, DISH2, created);
    }

    @Test
    public void update(){
        Dish updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        assertMatch(service.get(DISH1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    public void updateNotFound(){
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + DISH1_ID);
        Dish updated = getUpdated();
        service.update(updated, RESTAURANT3_ID);
    }

    @Test
    public void delete(){
        service.delete(DISH1_ID, RESTAURANT1_ID);
        assertMatch(service.getAllByState(RESTAURANT1_ID, State.STATE_ACTIVE), DISH2);
    }

    @Test
    public void notFoundDelete(){
        thrown.expect(NotFoundException.class);
        service.delete(DISH1_ID, RESTAURANT3_ID);
    }

    @Test
    public void get(){
        Dish dish = service.get(DISH1_ID, RESTAURANT1_ID);
        assertMatch(dish, DISH1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(DISH1_ID, RESTAURANT3_ID);
    }

    @Test
    public void getByName(){
        List<Dish> actual= service.getByName("Hot");
        assertMatch(actual, DISH4);
    }

    @Test
    public void getAll(){
        assertMatch(service.getAll(RESTAURANT1_ID), DISH1, DISH2, DISH3);
    }

    @Test
    public void getAllByState(){
        List<Dish> all = service.getAllByState(RESTAURANT1_ID,State.STATE_ACTIVE);
        assertMatch(all, DISH1, DISH2);
    }

    @Test
    public void getWithRestaurant(){
        Dish dish = service.getWithRestaurant(DISH1_ID, RESTAURANT1_ID);
        assertMatch(dish, DISH1);
        RestaurantTestData.assertMatch(dish.getRestaurant(), RESTAURANT1);
    }

    @Test
    public void getWithRestaurantNotFound(){
        thrown.expect(NotFoundException.class);
        service.getWithRestaurant(DISH1_ID, RESTAURANT3_ID);
    }
}