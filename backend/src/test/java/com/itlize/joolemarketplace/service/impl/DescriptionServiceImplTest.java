package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.DescriptionNotFoundException;
import com.itlize.joolemarketplace.model.Description;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.repository.DescriptionRepository;
import com.itlize.joolemarketplace.service.DescriptionService;

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
@ContextConfiguration(classes = { DescriptionServiceImpl.class })
class DescriptionServiceImplTest {

    @Autowired
    private DescriptionService descriptionService;

    @MockBean
    private DescriptionRepository descriptionRepository;

    @Test
    void createDescription() {
        Description description = new Description();
        description.setManufacturer("ford");
        description.setSeries("fly");
        description.setModel("aab");

        when(descriptionRepository.save(description)).thenReturn(description);
        Description createdDescription = descriptionService.createDescription(description);

        assertEquals(description, createdDescription);
        verify(descriptionRepository).save(description);
    }

    @Test
    void getDescriptionById() {
        Description description = new Description();
        description.setManufacturer("ford");
        description.setSeries("fly");
        description.setModel("aab");

        when(descriptionRepository.findById((Integer) any())).thenReturn(Optional.of(description));
        Optional<Description> foundOptionalDescription = descriptionService.getDescriptionById(1);

        assertTrue(foundOptionalDescription.isPresent());
        assertEquals(description, foundOptionalDescription.get());
        verify(descriptionRepository).findById(1);
    }

    @Test
    void getDescriptionByProduct() {
        Description description = new Description();
        description.setManufacturer("ford");
        description.setSeries("fly");
        description.setModel("aab");
        Product product = new Product();
        product.setProductBrand("foo");
        product.setCertification("bar");
        product.setDescription(description);
        description.setProduct(product);

        when(descriptionRepository.findByProduct(product)).thenReturn(Optional.of(description));
        Optional<Description> foundOptionalDescription = descriptionService.getDescriptionByProduct(product);

        assertTrue(foundOptionalDescription.isPresent());
        assertEquals(description, foundOptionalDescription.get());
        verify(descriptionRepository).findByProduct(product);
    }

    @Test
    void getAllDescription() {
        Description description1 = new Description();
        description1.setManufacturer("ford");
        description1.setSeries("fly");
        description1.setModel("aab");
        Description description2 = new Description();
        description2.setManufacturer("bmw");
        description2.setSeries("run");
        description2.setModel("bbc");

        when(descriptionRepository.findAll()).thenReturn(Arrays.asList(description1, description2));
        List<Description> foundDescription = descriptionService.getAllDescriptions();

        assertEquals(Arrays.asList(description1, description2), foundDescription);
        verify(descriptionRepository).findAll();
    }

    @Test
    void updateDescription() {
        Description description = new Description();
        description.setManufacturer("ford");
        description.setSeries("fly");
        description.setModel("aab");

        when(descriptionRepository.findById((Integer) any())).thenReturn(Optional.of(description));
        when(descriptionRepository.save(description)).thenReturn(description);
        Description updatedDescription = descriptionService.updateDescription(description);

        assertEquals(description, updatedDescription);
        verify(descriptionRepository).findById((Integer) any());
        verify(descriptionRepository).save(description);
    }

    @Test
    void updateDescriptionThatDoesNotExist() {
        Description description = new Description();
        description.setManufacturer("ford");
        description.setSeries("fly");
        description.setModel("aab");

        when(descriptionRepository.findById((Integer) any())).thenReturn(Optional.empty());

        assertThrows(DescriptionNotFoundException.class, () -> descriptionService.updateDescription(description));
        verify(descriptionRepository).findById((Integer) any());
        verify(descriptionRepository, never()).save(any());
    }

    @Test
    void deleteDescription() {
        Description description = new Description();
        description.setManufacturer("ford");
        description.setSeries("fly");
        description.setModel("aab");

        when(descriptionRepository.findById((Integer) any())).thenReturn(Optional.of(description));
        descriptionService.deleteDescription(1);

        verify(descriptionRepository).findById(1);
        verify(descriptionRepository).deleteById(1);
    }

    @Test
    void deleteDescriptionThatDoesNotExist() {
        Description description = new Description();
        description.setManufacturer("ford");
        description.setSeries("fly");
        description.setModel("aab");

        when(descriptionRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(DescriptionNotFoundException.class, () -> descriptionService.deleteDescription(1));
        verify(descriptionRepository).findById(1);
        verify(descriptionRepository, never()).deleteById(any());
    }
}