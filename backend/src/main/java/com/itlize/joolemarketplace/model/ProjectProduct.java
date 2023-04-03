package com.itlize.joolemarketplace.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProjectProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer projectProductId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProjectProduct(Project project, Product product) {
        this.project = project;
        this.product = product;
    }
}
