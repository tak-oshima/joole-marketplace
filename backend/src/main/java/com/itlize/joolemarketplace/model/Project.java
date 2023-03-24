package com.itlize.joolemarketplace.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    @ManyToOne
    @JoinColumn(name="user_name", nullable = false)
    private User user;

    @OneToMany(mappedBy="project", cascade= CascadeType.ALL, fetch=FetchType.LAZY)
    private List<ProjectProduct> projectProducts;

    public Project() {
        this.projectProducts = new ArrayList<>();
    }

    public Project(User user) {
        this.user = user;
        this.projectProducts = new ArrayList<>();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProjectProduct> getProjectProducts() {
        return projectProducts;
    }

    public void setProjectProducts(List<ProjectProduct> projectProducts) {
        this.projectProducts = projectProducts;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", userName='" + user + '\'' +
                '}';
    }
}
