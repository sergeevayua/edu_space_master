package ru.edu.space.app.repository;


import ru.edu.space.app.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author agerus
 */

public abstract class AbstractBaseEntityDao<PK extends Serializable, T extends BaseEntity> implements org.springframework.data.jpa.repository.JpaRepository<T, PK> {

}
