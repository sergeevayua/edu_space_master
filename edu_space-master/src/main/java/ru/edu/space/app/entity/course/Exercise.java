package ru.edu.space.app.entity.course;

import com.fasterxml.jackson.annotation.*;
import ru.edu.space.app.entity.AbstractBaseEntity;
import ru.edu.space.app.entity.EntityWithLongId;

import javax.persistence.*;

/**
 * @author agerus
 */
@Entity
@Table(name = "exercise")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, ignoreUnknown = true)
public class Exercise extends AbstractBaseEntity implements EntityWithLongId {

    private Long id;
    private Long numeration;
    private String description;
    private String content;
    private Chapter chapter;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "numeration")
    public Long getNumeration() {
        return numeration;
    }

    public void setNumeration(Long numeration) {
        this.numeration = numeration;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chapter_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("chapterId")
    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}
