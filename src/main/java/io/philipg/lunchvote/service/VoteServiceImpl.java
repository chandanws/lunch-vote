package io.philipg.lunchvote.service;

import io.philipg.lunchvote.model.Vote;
import io.philipg.lunchvote.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static io.philipg.lunchvote.util.DateTimeUtil.parseLocalTime;
import static io.philipg.lunchvote.util.ValidationUtil.checkAfterFinalHour;
import static io.philipg.lunchvote.util.ValidationUtil.checkNotFound;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;


    @Value("#{configProperties['vote.final_time']}")
    private String finalTimeString;

    @Autowired
    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote create(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        if (repository.get(vote.getRestaurantId(), vote.getUserId(), vote.getRegisteredDate())!=null){
            checkAfterFinalHour(vote.getRegistered().toLocalTime(), parseLocalTime(finalTimeString));
        }
        return repository.save(vote);
    }

    @Override
    public void update(Vote vote) {
        Assert.notNull(vote, "vote must not be null");
        repository.save(vote);
    }

    @Override
    public Vote get(int restaurantId, int userId, LocalDate registeredDate) {
        return checkNotFound(repository.get(restaurantId, userId, registeredDate), "vote must not be null");
    }

    @Override
    public List<Vote> getAllByRestaurant(int restaurantId) {
        return repository.getAllByRestaurant(restaurantId);
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return repository.getAllByUser(userId);
    }
}
