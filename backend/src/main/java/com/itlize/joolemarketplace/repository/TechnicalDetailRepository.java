package com.itlize.joolemarketplace.repository;

import com.itlize.joolemarketplace.dto.TechnicalDetailSearchCriteriaDto;
import com.itlize.joolemarketplace.model.TechnicalDetail;
import com.itlize.joolemarketplace.model.Product;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TechnicalDetailRepository extends JpaRepository<TechnicalDetail, Integer> {
    Optional<TechnicalDetail> findByProduct(Product product);

    @Query("SELECT td FROM TechnicalDetail td WHERE "
        + "(:minAirflow IS NULL OR td.airflow >= :minAirflow) AND "
        + "(:maxAirflow IS NULL OR td.airflow <= :maxAirflow) AND "
        + "(:minPower IS NULL OR td.power >= :minPower) AND "
        + "(:maxPower IS NULL OR td.power <= :maxPower) AND "
        + "(:minOperatingVoltage IS NULL OR td.operatingVoltage >= :minOperatingVoltage) AND "
        + "(:maxOperatingVoltage IS NULL OR td.operatingVoltage <= :maxOperatingVoltage) AND "
        + "(:minFanSpeed IS NULL OR td.fanSpeed >= :minFanSpeed) AND "
        + "(:maxFanSpeed IS NULL OR td.fanSpeed <= :maxFanSpeed)")
    List<TechnicalDetail> findBySearchCriteria(
            @Param("minAirflow") Integer minAirflow,
            @Param("maxAirflow") Integer maxAirflow,
            @Param("minPower") Integer minPower,
            @Param("maxPower") Integer maxPower,
            @Param("minOperatingVoltage") Integer minOperatingVoltage,
            @Param("maxOperatingVoltage") Integer maxOperatingVoltage,
            @Param("minFanSpeed") Integer minFanSpeed,
            @Param("maxFanSpeed") Integer maxFanSpeed
    );

    default List<TechnicalDetail> findBySearchCriteria(TechnicalDetailSearchCriteriaDto searchCriteria) {
        return findBySearchCriteria(
                searchCriteria.getMinAirflow(),
                searchCriteria.getMaxAirflow(),
                searchCriteria.getMinPower(),
                searchCriteria.getMaxPower(),
                searchCriteria.getMinOperatingVoltage(),
                searchCriteria.getMaxOperatingVoltage(),
                searchCriteria.getMinFanSpeed(),
                searchCriteria.getMaxFanSpeed()
        );
    }
}
