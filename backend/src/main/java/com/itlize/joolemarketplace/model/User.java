package com.itlize.joolemarketplace.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "user_password")
    private String userPassword;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="user",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Project> projects;

    public User() {
    }

    public User(String userName, String userType, String userPassword, List<Project> projects) {
        this.userName = userName;
        this.userType = userType;
        this.userPassword = userPassword;
        this.projects = projects;
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


    // add convenience methods for bi-directional relationship

    public void add(Project tempProject) {

        if (projects == null) {
            projects = new ArrayList<>();
        }

        projects.add(tempProject);

        tempProject.setUser(this);
    }

    // TODO: Implement toJson() method here
}
