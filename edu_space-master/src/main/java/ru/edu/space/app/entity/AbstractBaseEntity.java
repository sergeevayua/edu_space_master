package ru.edu.space.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author agerus
 */
@MappedSuperclass
public abstract class AbstractBaseEntity implements BaseEntity {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    private Date modificationTime;

    @Override
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modification_time")
    public Date getModificationTime() {
        return modificationTime;
    }

    @Override
    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        return object != null && getClass() == object.getClass();
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
