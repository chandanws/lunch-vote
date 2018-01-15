package io.philipg.lunchvote.repository.datajpa;

import io.philipg.lunchvote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    @Transactional
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v WHERE v.restaurantId=:restaurantId ORDER by v.registered ASC")
    List<Vote> getAllByRestaurant(@Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.userId=:userId ORDER by v.registered ASC")
    List<Vote> getAllByUser(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.restaurantId=:restaurantId AND v.userId=:userId AND v.registeredDate=:registeredDate")
    Vote get(@Param("restaurantId") int restaurantId, @Param("userId") int userId, @Param("registeredDate") LocalDate registeredDate);
}
