package io.philipg.lunchvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@IdClass(Vote.VotePK.class)
@Table(name = "votes" , uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "user_id", "registered_date"}, name = "votes_idx")})
public class Vote {

    @Id
    @Column(name = "restaurant_id", nullable = false)
    @NotNull
    private int restaurantId;

    @Id
    @Column(name = "user_id", nullable = false)
    @NotNull
    private int userId;

    @Id
    @Column(name = "registered_date", nullable = false)
    @NotNull
    private LocalDate registeredDate;

    @Column(name = "registered_time", nullable = false)
    @NotNull
    private LocalDateTime registered;

    public Vote(){}

    public Vote(@NotNull Restaurant restaurant, @NotNull User user, @NotNull LocalDateTime registered) {
        this.restaurantId = restaurant.id;
        this.userId = user.id;
        this.registeredDate = registered.toLocalDate();
        this.registered = registered;
    }

    public Vote(@NotNull int restaurantId, @NotNull int userId, @NotNull LocalDate registeredDate, @NotNull LocalDateTime registered) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.registeredDate = registeredDate;
        this.registered = registered;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    //https://stackoverflow.com/questions/3585034/how-to-map-a-composite-key-with-hibernate
    static class VotePK implements Serializable{

        protected int restaurantId;

        protected int userId;

        protected LocalDate registeredDate;

        public VotePK(){}

        public VotePK(int restaurantId, int userId, LocalDate registeredDate) {
            this.restaurantId = restaurantId;
            this.userId = userId;
            this.registeredDate = registeredDate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VotePK votePK = (VotePK) o;
            return restaurantId == votePK.restaurantId &&
                    userId == votePK.userId &&
                    Objects.equals(registeredDate, votePK.registeredDate);
        }

        @Override
        public int hashCode() {

            return Objects.hash(restaurantId, userId, registeredDate);
        }
    }
}
