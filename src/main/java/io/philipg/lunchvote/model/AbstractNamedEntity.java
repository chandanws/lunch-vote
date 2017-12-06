package io.philipg.lunchvote.model;

public abstract class AbstractNamedEntity extends AbstractBaseEntity {
    protected String name;

    public AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
