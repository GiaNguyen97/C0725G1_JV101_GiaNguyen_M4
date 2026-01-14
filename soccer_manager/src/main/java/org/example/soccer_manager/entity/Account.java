package org.example.soccer_manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "accounts")
public class Account extends Parent {

    @Id
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private AppRole appRole;

    public Account(String username, String password, Boolean isDeleted, Boolean isBlocked) {
        this.username = username;
        this.password = password;
        this.setDeleted(isDeleted);
        this.isBlocked = isBlocked;
    }

    public Account() {
    }

    public AppRole getAppRole() {
        return appRole;
    }

    public void setAppRole(AppRole appRole) {
        this.appRole = appRole;
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

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}