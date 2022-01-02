package com.kovalbooking.serviceidentity.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String username;
    private String password;
    @Enumerated
    private UserRole user_role;

    public User(String username, String password, UserRole user_role) {
        this.username = username;
        this.password = password;
        this.user_role = user_role;
    }

    public User() {
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUser_role() {
        return user_role;
    }

    public void setUser_role(UserRole user_role) {
        this.user_role = user_role;
    }
}
