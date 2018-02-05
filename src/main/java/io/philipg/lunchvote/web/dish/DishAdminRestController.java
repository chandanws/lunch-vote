package io.philipg.lunchvote.web.dish;

import io.philipg.lunchvote.model.Dish;
import io.philipg.lunchvote.model.Restaurant;
import io.philipg.lunchvote.model.State;
import io.philipg.lunchvote.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = DishAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = DishAdminRestController.REST_URL, description = "Dish mananegment")
public class DishAdminRestController {
    static final String REST_URL = "/api/admin/dishes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DishService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "View dishes with all states",
            notes = "Get available dishes",
            response = Restaurant.class,
            responseContainer = "List")
    public List<Dish> getAll(@RequestParam(value="restaurantId") int restaurantId){
        log.info("getAll by restaurant {}", restaurantId);
        return service.getAll(restaurantId);
    }

    @GetMapping(value = "/{dishId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish get(@PathVariable int dishId ){
        log.info("get {}", dishId);
        return service.get(dishId);
    }
}
