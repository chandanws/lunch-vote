package io.philipg.lunchvote.model;

import org.springframework.data.domain.Persistable;

public abstract class AbstractBaseEntity implements Persistable<Integer> {
    protected Integer id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
