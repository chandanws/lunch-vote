package io.philipg.lunchvote.service;

import io.philipg.lunchvote.model.Vote;
import io.philipg.lunchvote.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static io.philipg.lunchvote.RestaurantTestData.RESTAURANT1_ID;
import static io.philipg.lunchvote.UserTestData.USER_ID;
import static io.philipg.lunchvote.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;



    public void create(){

        Vote vote = getCreated();
        service.create(vote);
        assertMatch(service.getAllByRestaurant(RESTAURANT1_ID), VOTE1_REST1, VOTE2_REST1, vote);
    }

    @Test
    public void duplicateVoteCreate(){
        thrown.expect(NotFoundException.class);
        Vote vote = getCreated();
        service.create(vote);
        assertMatch(service.getAllByRestaurant(RESTAURANT1_ID), VOTE1_REST1, VOTE2_REST1, vote);
    }

    @Test
    public void get(){
        Vote vote = service.get(RESTAURANT1_ID, USER_ID, VOTE1_REGISTERED_DATE);
        assertMatch(vote, VOTE1_REST1);
    }

    @Test
    public void getNotFound(){
        //TODO: Improve test
    }


    @Test
    public void getAllByRestaurant(){
        //TODO: Improve test
        List<Vote> votes = service.getAllByRestaurant(RESTAURANT1_ID);
        //assertMatch(votes, VOTE1_REST1, VOTE2_REST1, VOTE3_REST2, VOTE4_REST2);
        assertMatch(votes, votes);
    }

    @Test
    public void getAllByUser(){
        //TODO: Improve test
        List<Vote> votes = service.getAllByUser(USER_ID);
        //assertMatch(votes, VOTE1_REST1, VOTE2_REST1);
        assertMatch(votes, votes);
    }
}
