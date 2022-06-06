package ru.edu.space.app.entity.user.support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author agerus
 */
public class UserDto {

    private String login;

    private String password;

    private Boolean isRemember = false;

    public String getLogin() {
        if (login.startsWith("+")) {
            return login.substring(1);
        }
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("remember")
    @JsonIgnore
    public Boolean getRemember() {
        return isRemember;
    }

    public void setRemember(Boolean remember) {
        isRemember = remember;
    }
}


