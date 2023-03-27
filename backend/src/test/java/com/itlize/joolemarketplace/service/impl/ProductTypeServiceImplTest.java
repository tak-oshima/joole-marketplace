package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.ProductTypeNotFoundException;
import com.itlize.joolemarketplace.model.ProductType;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.repository.ProductTypeRepository;
import com.itlize.joolemarketplace.service.ProductTypeService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ProductTypeServiceImpl.class })
class ProductTypeServiceImplTest {

    @Autowired
    private ProductTypeService productTypeService;

    @MockBean
    private ProductTypeRepository productTypeRepository;

    @Test
    void createProductType() {
        ProductType productType = new ProductType();
        productType.setApplication("phone");
        productType.setType("auto");
        productType.setMountingLocation("ppa");
        productType.setAccessories("wheel");
        productType.setModelYear(LocalDate.of(2009, 01, 01));

        when(productTypeRepository.save(productType)).thenReturn(productType);
        ProductType createdProductType = productTypeService.createProductType(productType);

        assertEquals(productType, createdProductType);
        verify(productTypeRepository).save(productType);
    }

    @Test
    void getProductTypeById() {
        ProductType productType = new ProductType();
        productType.setApplication("phone");
        productType.setType("auto");
        productType.setMountingLocation("ppa");
        productType.setAccessories("wheel");
        productType.setModelYear(LocalDate.of(2009, 01, 01));

        when(productTypeRepository.findById((Integer) any())).thenReturn(Optional.of(productType));
        Optional<ProductType> foundOptionalProductType = productTypeService.getProductTypeById(1);

        assertTrue(foundOptionalProductType.isPresent());
        assertEquals(productType, foundOptionalProductType.get());
        verify(productTypeRepository).findById(1);
    }

    @Test
    void getProductTypeByProduct() {
        ProductType productType = new ProductType();
        productType.setApplication("phone");
        productType.setType("auto");
        productType.setMountingLocation("ppa");
        productType.setAccessories("wheel");
        productType.setModelYear(LocalDate.of(2009, 01, 01));
        Product product = new Product();
        product.setProductBrand("foo");
        product.setCertification("bar");
        product.setProductType(productType);
        productType.setProduct(product);

        when(productTypeRepository.findByProduct(product)).thenReturn(Optional.of(productType));
        Optional<ProductType> foundOptionalProductType = productTypeService.getProductTypeByProduct(product);

        assertTrue(foundOptionalProductType.isPresent());
        assertEquals(productType, foundOptionalProductType.get());
        verify(productTypeRepository).findByProduct(product);
    }

    @Test
    void getAllProductType() {
        ProductType productType1 = new ProductType();
        productType1.setApplication("phone");
        productType1.setType("auto");
        productType1.setMountingLocation("ppa");
        productType1.setAccessories("wheel");
        productType1.setModelYear(LocalDate.of(2009, 01, 01));
        ProductType productType2 = new ProductType();
        productType2.setApplication("train");
        productType2.setType("manu");
        productType2.setMountingLocation("aaq");
        productType2.setAccessories("nno");
        productType2.setModelYear(LocalDate.of(2000, 01, 01));

        when(productTypeRepository.findAll()).thenReturn(Arrays.asList(productType1, productType2));
        List<ProductType> foundProductType = productTypeService.getAllProductTypes();

        assertEquals(Arrays.asList(productType1, productType2), foundProductType);
        verify(productTypeRepository).findAll();
    }

    @Test
    void updateProductType() {
        ProductType productType = new ProductType();
        productType.setApplication("phone");
        productType.setType("auto");
        productType.setMountingLocation("ppa");
        productType.setAccessories("wheel");
        productType.setModelYear(LocalDate.of(2009, 01, 01));

        when(productTypeRepository.findById((Integer) any())).thenReturn(Optional.of(productType));
        when(productTypeRepository.save(productType)).thenReturn(productType);
        ProductType updatedProductType = productTypeService.updateProductType(productType);

        assertEquals(productType, updatedProductType);
        verify(productTypeRepository).findById((Integer) any());
        verify(productTypeRepository).save(productType);
    }

    @Test
    void updateProductTypeThatDoesNotExist() {
        ProductType productType = new ProductType();
        productType.setApplication("phone");
        productType.setType("auto");
        productType.setMountingLocation("ppa");
        productType.setAccessories("wheel");
        productType.setModelYear(LocalDate.of(2009, 01, 01));

        when(productTypeRepository.findById((Integer) any())).thenReturn(Optional.empty());

        assertThrows(ProductTypeNotFoundException.class, () -> productTypeService.updateProductType(productType));
        verify(productTypeRepository).findById((Integer) any());
        verify(productTypeRepository, never()).save(any());
    }

    @Test
    void deleteProductType() {
        ProductType productType = new ProductType();
        productType.setApplication("phone");
        productType.setType("auto");
        productType.setMountingLocation("ppa");
        productType.setAccessories("wheel");
        productType.setModelYear(LocalDate.of(2009, 01, 01));

        when(productTypeRepository.findById((Integer) any())).thenReturn(Optional.of(productType));
        productTypeService.deleteProductType(1);

        verify(productTypeRepository).findById(1);
        verify(productTypeRepository).deleteById(1);
    }

    @Test
    void deleteProductTypeThatDoesNotExist() {
        ProductType productType = new ProductType();
        productType.setApplication("phone");
        productType.setType("auto");
        productType.setMountingLocation("ppa");
        productType.setAccessories("wheel");
        productType.setModelYear(LocalDate.of(2009, 01, 01));

        when(productTypeRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ProductTypeNotFoundException.class, () -> productTypeService.deleteProductType(1));
        verify(productTypeRepository).findById(1);
        verify(productTypeRepository, never()).deleteById(any());
    }
}