package com.itlize.joolemarketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="project_id")
    private Integer projectId;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_name", nullable = false)
    private User user;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="project",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<ProjectProduct> projectProducts;

    public Project() {
    }

    public Project(Integer projectId, User user) {
        this.projectId = projectId;
        this.user = user;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
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

    // TODO: Implement toJson() method here
}
