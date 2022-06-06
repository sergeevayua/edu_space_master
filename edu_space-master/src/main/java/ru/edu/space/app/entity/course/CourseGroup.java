package ru.edu.space.app.entity.course;

import com.fasterxml.jackson.annotation.*;
import ru.edu.space.app.entity.AbstractBaseEntity;
import ru.edu.space.app.entity.EntityWithLongId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author agerus
 */
@Entity
@Table(name = "course_group")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public class CourseGroup extends AbstractBaseEntity implements EntityWithLongId {

    private Long id;
    private String name;
    private Professional professional;
    private List<Course> course = new ArrayList<>();

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

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

    @OneToMany(mappedBy = "courseGroup")
    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "professional_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("professionalId")
    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }
}

