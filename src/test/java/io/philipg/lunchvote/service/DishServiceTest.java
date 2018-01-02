package io.philipg.lunchvote.service;

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
        service.create(created);
        assertMatch(service.getAllByState(State.STATE_ACTIVE), created, DISH1, DISH2, DISH4, DISH5);
    }

    @Test
    public void update(){
        Dish updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(DISH1_ID), updated);
    }

    //@Test
    public void updateNotFound(){
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + DISH1_ID);
        service.update(DISH2);
    }

    @Test
    public void delete(){
        service.delete(DISH1_ID);
        assertMatch(service.getAllByState(State.STATE_ACTIVE), DISH2, DISH4, DISH5);
    }

    @Test
    public void notFoundDelete(){
        thrown.expect(NotFoundException.class);
        service.delete(13);
    }

    @Test
    public void get(){
        Dish dish = service.get(DISH1_ID);
        assertMatch(dish, DISH1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(13);
    }

    @Test
    public void getByName(){
        List<Dish> actual= service.getByName("Hot");
        assertMatch(actual, DISH4);
    }

    @Test
    public void getAll(){
        assertMatch(service.getAll(), DISHES_ALL);
    }

    @Test
    public void getAllByState(){
        List<Dish> all = service.getAllByState(State.STATE_ACTIVE);
        assertMatch(all, DISHES_ACTIVE);
    }

    @Test
    public void getAllByStateNullPointerException() throws Exception {
        thrown.expect(NullPointerException.class);
        service.getAllByState(null);
    }
}