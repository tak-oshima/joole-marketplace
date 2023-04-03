package com.itlize.joolemarketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSearchCriteria {
    private ProductTypeSearchCriteria productTypeSearchCriteria;
    private TechnicalDetailSearchCriteria technicalDetailSearchCriteria;
    private String productBrand;
}
