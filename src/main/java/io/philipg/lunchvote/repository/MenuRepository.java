package io.philipg.lunchvote.repository;

import io.philipg.lunchvote.model.Menu;

public interface MenuRepository {

    Menu save (Menu menu);

    // false if not found
    boolean delete(int id);

    // null if not found
    Menu get(int restaurantId);

}
