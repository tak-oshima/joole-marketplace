package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.ProjectProductNotFoundException;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.model.Project;
import com.itlize.joolemarketplace.model.ProjectProduct;
import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.repository.ProjectProductRepository;
import com.itlize.joolemarketplace.service.ProjectProductService;
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
@ContextConfiguration(classes = {ProjectProductServiceImpl.class})
class ProjectProductServiceImplTest {
    @Autowired
    ProjectProductService projectProductService;

    @MockBean
    ProjectProductRepository projectProductRepository;

    @Test
    void createProjectProduct() {
        User user = new User();
        Project project = new Project(user);
        Product product = new Product();
        product.setProductBrand("foo");
        ProjectProduct projectProduct = new ProjectProduct(project, product);

        when(projectProductRepository.save(projectProduct)).thenReturn(projectProduct);
        ProjectProduct createdProjectProduct = projectProductService.createProjectProduct(projectProduct);

        assertEquals(projectProduct, createdProjectProduct);
        verify(projectProductRepository).save(projectProduct);
    }

    @Test
    void getProjectProductById() {
        User user = new User();
        Project project = new Project(user);
        Product product = new Product();
        product.setProductBrand("foo");
        ProjectProduct projectProduct = new ProjectProduct(project, product);

        when(projectProductRepository.findById((Integer) any())).thenReturn(Optional.of(projectProduct));
        Optional<ProjectProduct> foundOptionalProjectProduct = projectProductService.getProjectProductById(1);

        assertTrue(foundOptionalProjectProduct.isPresent());
        assertEquals(projectProduct, foundOptionalProjectProduct.get());
        verify(projectProductRepository).findById(1);
    }

    @Test
    void getAllProjectProducts() {
        User user = new User();
        Project project1 = new Project(user);
        Product product1 = new Product();
        product1.setProductBrand("foo");
        ProjectProduct projectProduct1 = new ProjectProduct(project1, product1);
        Project project2 = new Project(user);
        Product product2 = new Product();
        product2.setProductBrand("bar");
        ProjectProduct projectProduct2 = new ProjectProduct(project2, product2);

        when(projectProductRepository.findAll()).thenReturn(Arrays.asList(projectProduct1, projectProduct2));
        List<ProjectProduct> foundProjectProducts = projectProductService.getAllProjectProducts();

        assertEquals(Arrays.asList(projectProduct1, projectProduct2), foundProjectProducts);
        verify(projectProductRepository).findAll();
    }

    @Test
    void getProjectProductsByProject() {
        User user = new User();
        Project project1 = new Project(user);
        Product product1 = new Product();
        product1.setProductBrand("foo");
        ProjectProduct projectProduct1 = new ProjectProduct(project1, product1);
        Product product2 = new Product();
        product2.setProductBrand("bar");
        ProjectProduct projectProduct2 = new ProjectProduct(project1, product2);
        Project project2 = new Project(user);
        Product product3 = new Product();
        product3.setProductBrand("baz");
        ProjectProduct projectProduct3 = new ProjectProduct(project2, product3);

        when(projectProductRepository.findAllByProject(project1)).thenReturn(Arrays.asList(projectProduct1, projectProduct2));
        List<ProjectProduct> foundProjectProducts = projectProductService.getProjectProductsByProject(project1);

        assertEquals(Arrays.asList(projectProduct1, projectProduct2), foundProjectProducts);
        verify(projectProductRepository).findAllByProject(project1);
    }

    @Test
    void getProjectProductsByProduct() {
        User user = new User();
        Project project1 = new Project(user);
        Product product1 = new Product();
        product1.setProductBrand("foo");
        ProjectProduct projectProduct1 = new ProjectProduct(project1, product1);
        Project project2 = new Project(user);
        ProjectProduct projectProduct2 = new ProjectProduct(project2, product1);
        Project project3 = new Project(user);
        Product product2 = new Product();
        product2.setProductBrand("bar");
        ProjectProduct projectProduct3 = new ProjectProduct(project3, product2);

        when(projectProductRepository.findAllByProduct(product1)).thenReturn(Arrays.asList(projectProduct1, projectProduct2));
        List<ProjectProduct> foundProjectProducts = projectProductService.getProjectProductsByProduct(product1);

        assertEquals(Arrays.asList(projectProduct1, projectProduct2), foundProjectProducts);
        verify(projectProductRepository).findAllByProduct(product1);
    }

    @Test
    void updateProjectProduct() {
        User user = new User();
        Project project = new Project(user);
        Product product = new Product();
        product.setProductBrand("foo");
        ProjectProduct projectProduct = new ProjectProduct(project, product);

        when(projectProductRepository.findById((Integer) any())).thenReturn(Optional.of(projectProduct));
        when(projectProductRepository.save(projectProduct)).thenReturn(projectProduct);
        ProjectProduct updatedProjectProduct = projectProductService.updateProjectProduct(projectProduct);

        assertEquals(projectProduct, updatedProjectProduct);
        verify(projectProductRepository).findById((Integer) any());
        verify(projectProductRepository).save(projectProduct);
    }

    @Test
    void updateProjectProductThatDoesNotExist() {
        User user = new User();
        Project project = new Project(user);
        Product product = new Product();
        product.setProductBrand("foo");
        ProjectProduct projectProduct = new ProjectProduct(project, product);

        when(projectProductRepository.findById((Integer) any())).thenReturn(Optional.empty());

        assertThrows(ProjectProductNotFoundException.class, () -> projectProductService.updateProjectProduct(projectProduct));
        verify(projectProductRepository).findById((Integer) any());
        verify(projectProductRepository, never()).save(any());
    }

    @Test
    void deleteProjectProduct() {
        User user = new User();
        Project project = new Project(user);
        Product product = new Product();
        product.setProductBrand("foo");
        ProjectProduct projectProduct = new ProjectProduct(project, product);

        when(projectProductRepository.findById((Integer) any())).thenReturn(Optional.of(projectProduct));
        projectProductService.deleteProjectProduct(1);

        verify(projectProductRepository).findById(1);
        verify(projectProductRepository).deleteById(1);
    }

    @Test
    void deleteProjectProductThatDoesNotExist() {
        User user = new User();
        Project project = new Project(user);
        Product product = new Product();
        product.setProductBrand("foo");
        ProjectProduct projectProduct = new ProjectProduct(project, product);

        when(projectProductRepository.findById((Integer) any())).thenReturn(Optional.empty());

        assertThrows(ProjectProductNotFoundException.class, () -> projectProductService.deleteProjectProduct(1));
        verify(projectProductRepository).findById(1);
        verify(projectProductRepository, never()).deleteById(any());
    }
}