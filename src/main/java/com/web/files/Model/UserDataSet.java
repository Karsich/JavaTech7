package com.web.files.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "users")
public class UserProfile {
    @Id
    private String login;
    private String password;
    private String email;


    public UserProfile() {}
    public UserProfile(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
