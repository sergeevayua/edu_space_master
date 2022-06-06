package ru.edu.space.app.security.jwt.support;

import ru.edu.space.app.entity.user.Role;
import ru.edu.space.app.entity.user.User;

import java.util.List;

/**
 * @author agerus
 */
public class JWTDto {

    private String tokenId;

    private Long userId;

    private List<Role> roleId;

    public JWTDto(String tokenId, Long userId, List<Role> roleId) {
        this.tokenId = tokenId;
        this.userId = userId;
        this.roleId = roleId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public List<Role> getRoleId() {
        return roleId;
    }

    public void setRoleId(List<Role> roleId) {
        this.roleId = roleId;
    }
}
