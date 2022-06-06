package ru.edu.space.app.entity;

import java.io.Serializable;
import java.util.Date;

public interface BaseEntity extends Serializable {

    public Date getModificationTime();
    public void setModificationTime(Date modificationTime);
}
