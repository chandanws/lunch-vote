package io.philipg.lunchvote.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {
    private String address;

    private String phone;

    private String website;

    @Enumerated(EnumType.STRING)
    private State state = State.STATE_ACTIVE;

    public Restaurant(){}

    public Restaurant(Integer id, String name, String address, String phone, String website, State state) {
        super(id, name);
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", state=" + state +
                '}';
    }
}
