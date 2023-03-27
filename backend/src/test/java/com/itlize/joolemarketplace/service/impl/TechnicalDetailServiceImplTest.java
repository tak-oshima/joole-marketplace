package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.TechnicalDetailNotFoundException;
import com.itlize.joolemarketplace.model.TechnicalDetail;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.repository.TechnicalDetailRepository;
import com.itlize.joolemarketplace.service.TechnicalDetailService;

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
@ContextConfiguration(classes = { TechnicalDetailServiceImpl.class })
class TechnicalDetailServiceImplTest {

    @Autowired
    private TechnicalDetailService technicalDetailService;

    @MockBean
    private TechnicalDetailRepository technicalDetailRepository;

    @Test
    void createTechnicalDetail() {
        TechnicalDetail technicalDetail = new TechnicalDetail();
        technicalDetail.setAirflow(100);
        technicalDetail.setPower(50);
        technicalDetail.setOperatingVoltage(10);
        technicalDetail.setFanSpeed(40);

        when(technicalDetailRepository.save(technicalDetail)).thenReturn(technicalDetail);
        TechnicalDetail createdTechnicalDetail = technicalDetailService.createTechnicalDetail(technicalDetail);

        assertEquals(technicalDetail, createdTechnicalDetail);
        verify(technicalDetailRepository).save(technicalDetail);
    }

    @Test
    void getTechnicalDetailById() {
        TechnicalDetail technicalDetail = new TechnicalDetail();
        technicalDetail.setAirflow(100);
        technicalDetail.setPower(50);
        technicalDetail.setOperatingVoltage(10);
        technicalDetail.setFanSpeed(40);

        when(technicalDetailRepository.findById((Integer) any())).thenReturn(Optional.of(technicalDetail));
        Optional<TechnicalDetail> foundOptionalTechnicalDetail = technicalDetailService.getTechnicalDetailById(1);

        assertTrue(foundOptionalTechnicalDetail.isPresent());
        assertEquals(technicalDetail, foundOptionalTechnicalDetail.get());
        verify(technicalDetailRepository).findById(1);
    }

    @Test
    void getTechnicalDetailByProduct() {
        TechnicalDetail technicalDetail = new TechnicalDetail();
        technicalDetail.setAirflow(100);
        technicalDetail.setPower(50);
        technicalDetail.setOperatingVoltage(10);
        technicalDetail.setFanSpeed(40);
        Product product = new Product();
        product.setProductBrand("foo");
        product.setCertification("bar");
        product.setTechnicalDetail(technicalDetail);
        technicalDetail.setProduct(product);

        when(technicalDetailRepository.findByProduct(product)).thenReturn(Optional.of(technicalDetail));
        Optional<TechnicalDetail> foundOptionalTechnicalDetail = technicalDetailService
                .getTechnicalDetailByProduct(product);

        assertTrue(foundOptionalTechnicalDetail.isPresent());
        assertEquals(technicalDetail, foundOptionalTechnicalDetail.get());
        verify(technicalDetailRepository).findByProduct(product);
    }

    @Test
    void getAllTechnicalDetail() {
        TechnicalDetail technicalDetail1 = new TechnicalDetail();
        technicalDetail1.setAirflow(100);
        technicalDetail1.setPower(50);
        technicalDetail1.setOperatingVoltage(10);
        technicalDetail1.setFanSpeed(40);
        TechnicalDetail technicalDetail2 = new TechnicalDetail();
        technicalDetail2.setAirflow(200);
        technicalDetail2.setPower(100);
        technicalDetail2.setOperatingVoltage(20);
        technicalDetail2.setFanSpeed(10);

        when(technicalDetailRepository.findAll()).thenReturn(Arrays.asList(technicalDetail1, technicalDetail2));
        List<TechnicalDetail> foundTechnicalDetail = technicalDetailService.getAllTechnicalDetails();

        assertEquals(Arrays.asList(technicalDetail1, technicalDetail2), foundTechnicalDetail);
        verify(technicalDetailRepository).findAll();
    }

    @Test
    void updateTechnicalDetail() {
        TechnicalDetail technicalDetail = new TechnicalDetail();
        technicalDetail.setAirflow(100);
        technicalDetail.setPower(50);
        technicalDetail.setOperatingVoltage(10);
        technicalDetail.setFanSpeed(40);

        when(technicalDetailRepository.findById((Integer) any())).thenReturn(Optional.of(technicalDetail));
        when(technicalDetailRepository.save(technicalDetail)).thenReturn(technicalDetail);
        TechnicalDetail updatedTechnicalDetail = technicalDetailService.updateTechnicalDetail(technicalDetail);

        assertEquals(technicalDetail, updatedTechnicalDetail);
        verify(technicalDetailRepository).findById((Integer) any());
        verify(technicalDetailRepository).save(technicalDetail);
    }

    @Test
    void updateTechnicalDetailThatDoesNotExist() {
        TechnicalDetail technicalDetail = new TechnicalDetail();
        technicalDetail.setAirflow(100);
        technicalDetail.setPower(50);
        technicalDetail.setOperatingVoltage(10);
        technicalDetail.setFanSpeed(40);

        when(technicalDetailRepository.findById((Integer) any())).thenReturn(Optional.empty());

        assertThrows(TechnicalDetailNotFoundException.class,
                () -> technicalDetailService.updateTechnicalDetail(technicalDetail));
        verify(technicalDetailRepository).findById((Integer) any());
        verify(technicalDetailRepository, never()).save(any());
    }

    @Test
    void deleteTechnicalDetail() {
        TechnicalDetail technicalDetail = new TechnicalDetail();
        technicalDetail.setAirflow(100);
        technicalDetail.setPower(50);
        technicalDetail.setOperatingVoltage(10);
        technicalDetail.setFanSpeed(40);

        when(technicalDetailRepository.findById((Integer) any())).thenReturn(Optional.of(technicalDetail));
        technicalDetailService.deleteTechnicalDetail(1);

        verify(technicalDetailRepository).findById(1);
        verify(technicalDetailRepository).deleteById(1);
    }

    @Test
    void deleteTechnicalDetailThatDoesNotExist() {
        TechnicalDetail technicalDetail = new TechnicalDetail();
        technicalDetail.setAirflow(100);
        technicalDetail.setPower(50);
        technicalDetail.setOperatingVoltage(10);
        technicalDetail.setFanSpeed(40);

        when(technicalDetailRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(TechnicalDetailNotFoundException.class, () -> technicalDetailService.deleteTechnicalDetail(1));
        verify(technicalDetailRepository).findById(1);
        verify(technicalDetailRepository, never()).deleteById(any());
    }
}