package ru.edu.space.app.service;


import ru.edu.space.app.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author agerus
 */
public interface BaseEntityService<PK extends Serializable, T extends BaseEntity, D > extends EntityService<PK, T> {
}

