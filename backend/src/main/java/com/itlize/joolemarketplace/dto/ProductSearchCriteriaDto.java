package com.itlize.joolemarketplace.dto;

public class ProductSearchCriteriaDto {
    private ProductTypeSearchCriteriaDto productTypeSearchCriteriaDto;

    private TechnicalDetailSearchCriteriaDto technicalDetailSearchCriteriaDto;

    private String productBrand;

    public ProductSearchCriteriaDto() {
    }

    public ProductSearchCriteriaDto(ProductTypeSearchCriteriaDto productTypeSearchCriteriaDto, TechnicalDetailSearchCriteriaDto technicalDetailSearchCriteriaDto, String productBrand) {
        this.productTypeSearchCriteriaDto = productTypeSearchCriteriaDto;
        this.technicalDetailSearchCriteriaDto = technicalDetailSearchCriteriaDto;
        this.productBrand = productBrand;
    }

    public ProductTypeSearchCriteriaDto getProductTypeSearchDto() {
        return productTypeSearchCriteriaDto;
    }

    public void setProductTypeSearchCriteriaDto(ProductTypeSearchCriteriaDto productTypeSearchCriteriaDto) {
        this.productTypeSearchCriteriaDto = productTypeSearchCriteriaDto;
    }

    public TechnicalDetailSearchCriteriaDto getTechnicalDetailSearchCriteriaDto() {
        return technicalDetailSearchCriteriaDto;
    }

    public void setTechnicalDetailSearchCriteriaDto(TechnicalDetailSearchCriteriaDto technicalDetailSearchCriteriaDto) {
        this.technicalDetailSearchCriteriaDto = technicalDetailSearchCriteriaDto;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    @Override
    public String toString() {
        return "ProductSearchCriteriaDto{" +
                "productTypeSearchCriteriaDto=" + productTypeSearchCriteriaDto +
                ", technicalDetailSearchCriteriaDto=" + technicalDetailSearchCriteriaDto +
                ", productBrand='" + productBrand + '\'' +
                '}';
    }

    // TODO: Clarify "Other: With the words" field
}
