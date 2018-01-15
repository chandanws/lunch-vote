package io.philipg.lunchvote.repository.datajpa;

import io.philipg.lunchvote.model.Vote;
import io.philipg.lunchvote.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudVoteRepository crudRepository;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        return crudRepository.save(vote);
    }

    @Override
    public Vote get(int restaurantId, int userId, LocalDate registeredDate) {
        return crudRepository.get(restaurantId, userId, registeredDate);
    }

    @Override
    public List<Vote> getAllByRestaurant(int restaurantId) {
        return crudRepository.getAllByRestaurant(restaurantId);
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return crudRepository.getAllByUser(userId);
    }
}
