package io.philipg.lunchvote.repository.datajpa;

import io.philipg.lunchvote.model.Dish;
import io.philipg.lunchvote.model.Restaurant;
import io.philipg.lunchvote.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Dish d SET d.state='STATE_REMOVED' WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Override
    Dish save(Dish dish);

    @Query("SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.id =:id and d.restaurant.id=:restaurantId")
    Dish getWithRestaurant(@Param("id") int id, @Param("restaurantId") int restaurantId);

    List<Dish> findByNameContaining(String name);

    @Override
    Optional<Dish> findById(Integer id);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId ORDER BY d.name ASC")
    List<Dish> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.state IN :states")
    List<Dish> getAllByStateIn(@Param("restaurantId") int restaurantId, @Param("states") Iterable<State> states);
}