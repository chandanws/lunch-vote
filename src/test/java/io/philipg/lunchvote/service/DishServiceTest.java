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
        Dish created = new Dish(null, "Apple pie", 10, "Apple pie", State.STATE_ACTIVE);
        service.create(created);
        assertMatch(service.getAll(), created, DISH1, DISH2, DISH3, DISH4, DISH5, DISH6);
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
        assertMatch(service.getAll(), DISH2, DISH3, DISH4, DISH5, DISH6);
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

    @Test(expected = NotFoundException.class)
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
        assertMatch(service.getAll(), DISHES);
    }
}