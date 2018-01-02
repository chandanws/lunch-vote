package io.philipg.lunchvote;

import io.philipg.lunchvote.model.Dish;
import io.philipg.lunchvote.model.State;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static io.philipg.lunchvote.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {

    public static final int DISH1_ID = START_SEQ + 2;

    public static final Dish DISH1 = new Dish(DISH1_ID, "Cheeseburger", 30, "Cheeseburger Ingredients", State.STATE_ACTIVE);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "French fries", 15, "French fries Ingredients", State.STATE_ACTIVE);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Hamburger", 30, "Hamburger Ingredients", State.STATE_REMOVED);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "Hot dog", 25, "Hot Dog Ingredients", State.STATE_ACTIVE);
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, "Pasta", 50, "Pasta Ingredients",State.STATE_ACTIVE);
    public static final Dish DISH6 = new Dish(DISH1_ID + 5, "Sandwich", 20, "Sandwich Ingredients", State.STATE_REMOVED);

    public static Dish getCreated() {
        return new Dish(null, "Apple pie", 10, "Apple pie", State.STATE_ACTIVE);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "Updated Cheeseburger", 11, "Cheeseburger Ingredients", State.STATE_REMOVED);
    }

    public static final List<Dish> DISHES_ALL = Arrays.asList(DISH1, DISH2, DISH3, DISH4, DISH5, DISH6);

    public static final List<Dish> DISHES_ACTIVE = Arrays.asList(DISH1, DISH2, DISH4, DISH5);

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected){
        assertThat(actual).usingElementComparatorIgnoringFields("").isEqualTo(expected);
    }
}