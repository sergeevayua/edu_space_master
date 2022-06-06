package ru.edu.space.app.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.edu.space.app.entity.user.User;


/**
 * @author agerus
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user WHERE user.login = :login OR user.email = :email OR user.phone = :phone")
    public User getUserByEmailOrPhoneOrLogin(@Param("email") String email,
                                             @Param("phone") String phone,
                                             @Param("login") String login);

    @Query("SELECT user FROM User user WHERE user.login = :login")
    public User getUserByLogin(@Param("login") String login);

    @Query("SELECT user FROM User user WHERE user.email = :email")
    public User getUserByEmail(@Param("email") String email);

    @Query("SELECT user FROM User user WHERE user.phone = :phone")
    public User getUserByPhone(@Param("phone") String phone);
}
