package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.model.TechnicalDetail;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.repository.TechnicalDetailRepository;
import com.itlize.joolemarketplace.service.TechnicalDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TechnicalDetailServiceImpl implements TechnicalDetailService {
    @Autowired
    private TechnicalDetailRepository technicalDetailRepository;

    @Override
    public TechnicalDetail createTechnicalDetail(TechnicalDetail technicalDetail) {
        return technicalDetailRepository.save(technicalDetail);
    }

    @Override
    public Optional<TechnicalDetail> getTechnicalDetailById(Integer technicalDetailId) {
        return technicalDetailRepository.findById(technicalDetailId);
    }

    @Override
    public Optional<TechnicalDetail> getTechnicalDetailByProduct(Product product) {
        return technicalDetailRepository.findByProduct(product);
    }

    @Override
    public List<TechnicalDetail> getAllTechnicalDetails() {
        return technicalDetailRepository.findAll();
    }

    @Override
    public TechnicalDetail updateTechnicalDetail(TechnicalDetail technicalDetail) {
        if (!technicalDetailRepository.findById(technicalDetail.getTechnicalDetailId()).isPresent()) {
            throw new RuntimeException(
                    String.format("Update TechnicalDetail Exception: technicalDetail with technicalDetail_id \"%d\" not found", technicalDetail.getTechnicalDetailId())
            );
        }
        return technicalDetailRepository.save(technicalDetail);
    }

    @Override
    public void deleteTechnicalDetail(Integer technicalDetailId) {
        if (!technicalDetailRepository.findById(technicalDetailId).isPresent()) {
            throw new RuntimeException(
                    String.format("Delete TechnicalDetail Exception: technicalDetail with technicalDetail_id \"%d\" not found", technicalDetailId)
            );
        }
        technicalDetailRepository.deleteById(technicalDetailId);
    }
}
