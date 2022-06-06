package ru.edu.space.app.entity.course;

import ru.edu.space.app.entity.AbstractBaseEntity;
import ru.edu.space.app.entity.EntityWithLongId;

import javax.persistence.*;

/**
 * @author agerus
 */
@Entity
@Table(name = "professional")
public class Professional extends AbstractBaseEntity implements EntityWithLongId {

    private Long id;
    private String name;
    private String link;
    private String icon;

    @Override
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
