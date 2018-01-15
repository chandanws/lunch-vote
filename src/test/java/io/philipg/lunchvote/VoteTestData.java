package io.philipg.lunchvote;

import io.philipg.lunchvote.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

import static io.philipg.lunchvote.RestaurantTestData.RESTAURANT1;
import static io.philipg.lunchvote.RestaurantTestData.RESTAURANT2;
import static io.philipg.lunchvote.UserTestData.USER;

public class VoteTestData {

    public static final Vote VOTE1_REST1 = new Vote(RESTAURANT1, USER, LocalDateTime.now());
    public static final Vote VOTE2_REST1 = new Vote(RESTAURANT1, USER, LocalDateTime.now().plusDays(1));
    public static final Vote VOTE3_REST2 = new Vote(RESTAURANT2, USER, LocalDateTime.now());
    public static final Vote VOTE4_REST2 = new Vote(RESTAURANT2, USER, LocalDateTime.now().plusDays(1));

    public static final LocalDate VOTE1_REGISTERED_DATE = LocalDate.now();

    public static Vote getCreated() {
        return new Vote (RESTAURANT1, USER, LocalDateTime.now());
    }

    public static void assertMatch(Vote actual, Vote expected){
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected){
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected){
        assertThat(actual).usingElementComparatorIgnoringFields("registered").isEqualTo(expected);
    }
}
