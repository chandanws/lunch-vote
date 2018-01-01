package io.philipg.lunchvote.service;

import io.philipg.lunchvote.model.Restaurant;
import io.philipg.lunchvote.repository.JpaUtil;
import io.philipg.lunchvote.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.util.List;

import static io.philipg.lunchvote.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected  RestaurantService service;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    protected JpaUtil jpaUtil;

    public void setUp(){
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

     @Test
     public void create() throws Exception {
         Restaurant newRestaurant = getCreated();
         Restaurant created = service.create(newRestaurant);
         newRestaurant.setId(created.getId());
         assertMatch(service.getAll(), RESTAURANT1, RESTAURANT2, created);
     }

     //TODO Check duplicate restaurant
    public void duplicateNameCreate() throws Exception {}

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT1_ID);
        assertMatch(service.getAll(), RESTAURANT2);
    }

    @Test
    public void notFoundDelete() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(1);
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(1);
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = getUpdated();
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(service.get(RESTAURANT1_ID), updated);
    }

    @Test
    public void getAll() throws Exception {
        List<Restaurant> all = service.getAll();
        assertMatch(all, RESTAURANT1, RESTAURANT2);
    }
}