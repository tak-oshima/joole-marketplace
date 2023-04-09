package com.itlize.joolemarketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class User {
    @Id
    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userType;

    @Column(nullable = false)
    private String userPassword;

    @JsonIgnore
    @OneToMany(mappedBy="user", cascade= CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Project> projects;

    public User() {
        this.projects = new ArrayList<>();
    }

    public User(String userName, String userType, String userPassword) {
        this.userName = userName;
        this.userType = userType;
        this.userPassword = userPassword;
        this.projects = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(userType, user.userType) &&
                Objects.equals(userPassword, user.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userType,userPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                ", userPassword=" + userPassword + '\'' +
                '}';
    }
}
