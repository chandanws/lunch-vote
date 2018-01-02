package io.philipg.lunchvote.repository.datajpa;

import io.philipg.lunchvote.model.Restaurant;
import io.philipg.lunchvote.model.State;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Restaurant r SET r.state='STATE_REMOVED' WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Restaurant save (Restaurant restaurant);

    @Override
    Optional<Restaurant> findById(Integer id);

    @Override
    List<Restaurant> findAll(Sort sort);

    List<Restaurant> findAllByStateEquals(State state, Sort sort);
}