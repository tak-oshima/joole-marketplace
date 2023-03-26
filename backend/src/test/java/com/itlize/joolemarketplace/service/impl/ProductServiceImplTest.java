package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.ProductNotFoundException;
import com.itlize.joolemarketplace.model.Description;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.ProductType;
import com.itlize.joolemarketplace.model.TechnicalDetail;
import com.itlize.joolemarketplace.repository.ProductRepository;
import com.itlize.joolemarketplace.service.ProductService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProductServiceImpl.class})
class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void createProduct() {
        Product product = new Product();
        product.setProductBrand("foo");
        product.setCertification("bar");

        when(productRepository.save(product)).thenReturn(product);
        Product createdProduct = productService.createProduct(product);

        assertEquals(product, createdProduct);
        verify(productRepository).save(product);
    }

    @Test
    void getProductById() {
        Product product = new Product();
        product.setProductBrand("foo");
        product.setCertification("bar");

        when(productRepository.findById((Integer) any())).thenReturn(Optional.of(product));
        Optional<Product> foundOptionalProduct = productService.getProductById(1);

        assertTrue(foundOptionalProduct.isPresent());
        assertEquals(product, foundOptionalProduct.get());
        verify(productRepository).findById(1);
    }

    @Test
    void getProductByProductType() {
        Product product = new Product();
        product.setProductBrand("foo");
        product.setCertification("bar");
        ProductType productType = new ProductType();
        productType.setProduct(product);
        product.setProductType(productType);

        when(productRepository.findByProductType(productType)).thenReturn(Optional.of(product));
        Optional<Product> foundOptionalProduct = productService.getProductByProductType(productType);

        assertTrue(foundOptionalProduct.isPresent());
        assertEquals(product, foundOptionalProduct.get());
        verify(productRepository).findByProductType(productType);
    }

    @Test
    void getProductByTechnicalDetail() {
        Product product = new Product();
        product.setProductBrand("foo");
        product.setCertification("bar");
        TechnicalDetail technicalDetail = new TechnicalDetail();
        technicalDetail.setProduct(product);
        product.setTechnicalDetail(technicalDetail);

        when(productRepository.findByTechnicalDetail(technicalDetail)).thenReturn(Optional.of(product));
        Optional<Product> foundOptionalProduct = productService.getProductByTechnicalDetail(technicalDetail);

        assertTrue(foundOptionalProduct.isPresent());
        assertEquals(product, foundOptionalProduct.get());
        verify(productRepository).findByTechnicalDetail(technicalDetail);
    }

    @Test
    void getProductByDescription() {
        Product product = new Product();
        product.setProductBrand("foo");
        product.setCertification("bar");
        Description description = new Description();
        description.setProduct(product);
        product.setDescription(description);

        when(productRepository.findByDescription(description)).thenReturn(Optional.of(product));
        Optional<Product> foundOptionalProduct = productService.getProductByDescription(description);

        assertTrue(foundOptionalProduct.isPresent());
        assertEquals(product, foundOptionalProduct.get());
        verify(productRepository).findByDescription(description);
    }

    @Test
    void getAllProducts() {
        Product product1 = new Product();
        product1.setProductBrand("foo");
        product1.setCertification("bar");
        Product product2 = new Product();
        product2.setProductBrand("baz");
        product2.setCertification("qux");

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        List<Product> foundProducts = productService.getAllProducts();

        assertEquals(Arrays.asList(product1, product2), foundProducts);
        verify(productRepository).findAll();
    }

    @Test
    void getProductsByBrand() {
        Product product1 = new Product();
        product1.setProductBrand("foo");
        product1.setCertification("bar");
        Product product2 = new Product();
        product2.setProductBrand("foo");
        product2.setCertification("baz");
        Product product3 = new Product();
        product3.setProductBrand("qux");
        product3.setCertification("quux");

        when(productRepository.findAllByProductBrand("foo")).thenReturn(Arrays.asList(product1, product2));
        List<Product> foundProducts = productService.getProductsByBrand("foo");

        assertEquals(Arrays.asList(product1, product2), foundProducts);
        verify(productRepository).findAllByProductBrand("foo");
    }

    @Test
    void updateProduct() {
        Product product = new Product();
        product.setProductBrand("foo");
        product.setCertification("bar");

        when(productRepository.findById((Integer) any())).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        Product updatedProduct = productService.updateProduct(product);

        assertEquals(product, updatedProduct);
        verify(productRepository).findById((Integer) any());
        verify(productRepository).save(product);
    }

    @Test
    void updateProductThatDoesNotExist() {
        Product product = new Product();
        product.setProductBrand("foo");
        product.setCertification("bar");

        when(productRepository.findById((Integer) any())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(product));
        verify(productRepository).findById((Integer) any());
        verify(productRepository, never()).save(any());
    }

    @Test
    void deleteProduct() {
        Product product = new Product();
        product.setProductBrand("foo");
        product.setCertification("bar");

        when(productRepository.findById((Integer) any())).thenReturn(Optional.of(product));
        productService.deleteProduct(1);

        verify(productRepository).findById(1);
        verify(productRepository).deleteById(1);
    }

    @Test
    void deleteProductThatDoesNotExist() {
        Product product = new Product();
        product.setProductBrand("foo");
        product.setCertification("bar");

        when(productRepository.findById((Integer) any())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(1));
        verify(productRepository).findById(1);
        verify(productRepository, never()).deleteById(any());
    }
}
