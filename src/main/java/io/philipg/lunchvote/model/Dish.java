package io.philipg.lunchvote.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table (name = "dishes")
public class Dish extends AbstractNamedEntity {

    private long price;

    private String description;

    @Enumerated(EnumType.STRING)
    private State state = State.STATE_ACTIVE;

    public Dish() {}

    public Dish(Integer id, String name, long price, String description, State state) {
        super(id, name);
        this.price = price;
        this.description = description;
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Dish{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                "price=" + price +
                ", description='" + description + '\'' +
                ", state=" + state +
                '}';
    }
}
