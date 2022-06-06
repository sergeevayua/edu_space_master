package ru.edu.space.app.entity;

import java.io.Serializable;

/**
 * @author agerus
 */
public interface EntityWithId<PK extends Serializable> extends BaseEntity {

    public PK getId();

    public void setId(PK id);
}

