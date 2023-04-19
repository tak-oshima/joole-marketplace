package com.itlize.joolemarketplace.repository.specs;

import com.itlize.joolemarketplace.dto.ProductTypeSearchRequest;
import com.itlize.joolemarketplace.model.ProductType;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeSpecs {
    public static Specification<ProductType> matchesSearchCriteria(ProductTypeSearchRequest searchCriteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchCriteria.getApplication() != null)
                predicates.add(criteriaBuilder.equal(root.get("application"), searchCriteria.getApplication()));
            if (searchCriteria.getType() != null)
                predicates.add(criteriaBuilder.equal(root.get("type"), searchCriteria.getType()));
            if (searchCriteria.getMountingLocation() != null)
                predicates.add(criteriaBuilder.equal(root.get("mountingLocation"), searchCriteria.getMountingLocation()));
            if (searchCriteria.getAccessories() != null)
                predicates.add(criteriaBuilder.equal(root.get("accessories"), searchCriteria.getAccessories()));
            if (searchCriteria.getMinModelYear() != null)
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("modelYear"), searchCriteria.getMinModelYear()));
            if (searchCriteria.getMaxModelYear() != null)
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("modelYear"), searchCriteria.getMaxModelYear()));

            return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
        };
    }
}
