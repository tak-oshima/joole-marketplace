package com.itlize.joolemarketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer projectId;

    @ManyToOne
    @JoinColumn(name="username", nullable = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy="project", cascade= CascadeType.ALL, fetch=FetchType.LAZY)
    private List<ProjectProduct> projectProducts = new ArrayList<>();

    private String projectName;

    public Project(User user) {
        this.user = user;
    }
}
