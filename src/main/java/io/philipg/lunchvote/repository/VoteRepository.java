package io.philipg.lunchvote.repository;

import io.philipg.lunchvote.model.Vote;

import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    // false if not found
    boolean delete(int id);

    // null if not found
    Vote get(int restaurantId, int userId);

    List<Vote> getAll(int restaurantId);

}
