package com.itlize.joolemarketplace.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSearchRequest {
    private ProductTypeSearchRequest productTypeSearchRequest;
    private TechnicalDetailSearchRequest technicalDetailSearchRequest;
    private String productBrand;
}
