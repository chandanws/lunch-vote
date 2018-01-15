package io.philipg.lunchvote.repository;

import io.philipg.lunchvote.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    // null if not found
    Vote get(int restaurantId, int userId, LocalDate registeredDate);

    //TODO: Need to think
    // User getByDate(Date date);

    List<Vote> getAllByRestaurant(int restaurantId);

    List<Vote> getAllByUser(int userId);
}
