package io.philipg.lunchvote.service;

import io.philipg.lunchvote.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    Vote create(Vote vote);

    void update(Vote vote);

    Vote get(int restaurantId, int userId, LocalDate registeredDate);

    List<Vote> getAllByRestaurant(int restaurantId);

    List<Vote> getAllByUser(int userId);
}
