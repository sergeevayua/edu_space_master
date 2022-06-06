package ru.edu.space.app.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.space.app.entity.user.UserToken;

@Repository
public interface UserTokenDao extends JpaRepository<UserToken, Long> {

    @Query("SELECT token FROM UserToken token WHERE token.token = :token")
    UserToken getByToken(@Param("token") String token);
}
