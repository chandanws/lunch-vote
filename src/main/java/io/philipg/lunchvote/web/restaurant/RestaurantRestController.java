package io.philipg.lunchvote.web.restaurant;

import io.philipg.lunchvote.model.Restaurant;
import io.philipg.lunchvote.model.State;
import io.philipg.lunchvote.service.RestaurantService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = RestaurantRestController.REST_URL, description = "Operations about restaurants")
public class RestaurantRestController {
    static final String REST_URL = "/api/restaurants";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "View restaurants",
            notes = "Get available restaurants",
            response = Restaurant.class,
            responseContainer = "List")
    public List<Restaurant> getAllByState(){
        log.info("getAllByState");
        return service.getAllByState(State.STATE_ACTIVE);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable int id){
        log.info("get {}", id);
        return service.get(id);
    }
}
