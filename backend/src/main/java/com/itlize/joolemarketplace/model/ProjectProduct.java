package com.itlize.joolemarketplace.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProjectProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectProductId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProjectProduct() {
    }

    public ProjectProduct(Project project, Product product) {
        this.project = project;
        this.product = product;
    }

    public Integer getProjectProductId() {
        return projectProductId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectProduct that = (ProjectProduct) o;
        return Objects.equals(projectProductId, that.projectProductId) &&
                Objects.equals(project, that.project) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectProductId, project, product);
    }

    @Override
    public String toString() {
        return "ProjectProduct{" +
                "projectProductId=" + projectProductId +
                ", project=" + project +
                ", product=" + product +
                '}';
    }
}
