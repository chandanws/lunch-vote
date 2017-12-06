package io.philipg.lunchvote.model;

public abstract class AbstractBaseEntity {
    protected Integer id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

}
