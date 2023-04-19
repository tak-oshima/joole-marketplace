package com.itlize.joolemarketplace.repository.specs;

import com.itlize.joolemarketplace.dto.TechnicalDetailSearchRequest;
import com.itlize.joolemarketplace.model.TechnicalDetail;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class TechnicalDetailSpecs {
    public static Specification<TechnicalDetail> matchesSearchCriteria(TechnicalDetailSearchRequest searchCriteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchCriteria.getMinAirflow() != null)
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("airflow"), searchCriteria.getMinAirflow()));
            if (searchCriteria.getMaxAirflow() != null)
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("airflow"), searchCriteria.getMaxAirflow()));
            if (searchCriteria.getMinPower() != null)
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("power"), searchCriteria.getMinPower()));
            if (searchCriteria.getMaxPower() != null)
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("power"), searchCriteria.getMaxPower()));
            if (searchCriteria.getMinOperatingVoltage() != null)
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("operatingVoltage"), searchCriteria.getMinOperatingVoltage()));
            if (searchCriteria.getMaxOperatingVoltage() != null)
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("operatingVoltage"), searchCriteria.getMaxOperatingVoltage()));
            if (searchCriteria.getMinFanSpeed() != null)
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("fanSpeed"), searchCriteria.getMinFanSpeed()));
            if (searchCriteria.getMaxPower() != null)
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("fanSpeed"), searchCriteria.getMaxFanSpeed()));

            return criteriaBuilder.and(predicates.stream().toArray(Predicate[]::new));
        };
    }
}
