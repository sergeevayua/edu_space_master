package ru.edu.space.app.entity.user;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "token")
public class UserToken {

    private Long id;

    private User user;

    private String token;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToken userToken = (UserToken) o;
        return Objects.equals(id, userToken.id) &&
                Objects.equals(token, userToken.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
