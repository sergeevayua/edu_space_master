package ru.edu.space.app.entity.course;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import ru.edu.space.app.entity.AbstractBaseEntity;
import ru.edu.space.app.entity.EntityWithLongId;

import javax.persistence.*;
import java.util.List;


/**
 * @author agerus
 */
@Entity
@Table(name = "course")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public class Course extends AbstractBaseEntity implements EntityWithLongId {

    private Long id;
    private String name;
    private CourseGroup courseGroup;
    private List<Module> modules;
    private Boolean isAllowToView = false;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_group_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("courseGroupId")
    public CourseGroup getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(CourseGroup courseGroup) {
        this.courseGroup = courseGroup;
    }

    @Column(name = "is_allow_to_view")
    public Boolean getAllowToView() {
        return isAllowToView;
    }

    public void setAllowToView(Boolean allowToView) {
        isAllowToView = allowToView;
    }

    @OneToMany(mappedBy = "course")
    @LazyCollection(LazyCollectionOption.TRUE)
    @JsonIgnore
    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
