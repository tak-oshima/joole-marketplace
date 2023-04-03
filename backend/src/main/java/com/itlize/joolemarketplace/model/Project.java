package com.itlize.joolemarketplace.model;

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
    @JoinColumn(name="user_name", nullable = false)
    private User user;

    @OneToMany(mappedBy="project", cascade= CascadeType.ALL, fetch=FetchType.LAZY)
    private List<ProjectProduct> projectProducts = new ArrayList<>();

    public Project(User user) {
        this.user = user;
    }
}
