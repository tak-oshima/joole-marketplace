package com.itlize.joolemarketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer productId;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProjectProduct> projectProducts = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @OneToOne
    @JoinColumn(name = "technical_detail_id")
    private TechnicalDetail technicalDetail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private Description description;

    private String productBrand;

    private String certification;
}
