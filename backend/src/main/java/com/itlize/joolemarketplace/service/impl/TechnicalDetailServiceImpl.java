package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.TechnicalDetailNotFoundException;
import com.itlize.joolemarketplace.model.TechnicalDetail;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.repository.TechnicalDetailRepository;
import com.itlize.joolemarketplace.service.TechnicalDetailService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TechnicalDetailServiceImpl implements TechnicalDetailService {
    private final TechnicalDetailRepository technicalDetailRepository;

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
            throw new TechnicalDetailNotFoundException(technicalDetail.getTechnicalDetailId());
        }
        return technicalDetailRepository.save(technicalDetail);
    }

    @Override
    public void deleteTechnicalDetail(Integer technicalDetailId) {
        if (!technicalDetailRepository.findById(technicalDetailId).isPresent()) {
            throw new TechnicalDetailNotFoundException(technicalDetailId);
        }
        technicalDetailRepository.deleteById(technicalDetailId);
    }
}
