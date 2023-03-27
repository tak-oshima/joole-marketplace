package com.itlize.joolemarketplace.repository;

import com.itlize.joolemarketplace.dto.ProductTypeSearchCriteriaDto;
import com.itlize.joolemarketplace.model.ProductType;
import com.itlize.joolemarketplace.model.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
    Optional<ProductType> findByProduct(Product product);

    @Query("SELECT pt FROM ProductType pt WHERE "
            + "(:application IS NULL OR pt.application = :application) AND "
            + "(:type IS NULL OR pt.type = :type) AND "
            + "(:mountingLocation IS NULL OR pt.mountingLocation = :mountingLocation) AND "
            + "(:accessories IS NULL OR pt.accessories = :accessories) AND "
            + "(:minModelYear IS NULL OR pt.modelYear >= :minModelYear) AND "
            + "(:maxModelYear IS NULL OR pt.modelYear <= :maxModelYear)")
    List<ProductType> findBySearchCriteria(
            @Param("application") String application,
            @Param("type") String type,
            @Param("mountingLocation") String mountingLocation,
            @Param("accessories") String accessories,
            @Param("minModelYear") LocalDate minModelYear,
            @Param("maxModelYear") LocalDate maxModelYear);

    default List<ProductType> findBySearchCriteria(ProductTypeSearchCriteriaDto searchCriteria) {
        return findBySearchCriteria(
                searchCriteria.getApplication(),
                searchCriteria.getType(),
                searchCriteria.getMountingLocation(),
                searchCriteria.getAccessories(),
                searchCriteria.getMinModelYear(),
                searchCriteria.getMaxModelYear());
    }
}
