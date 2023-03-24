package com.itlize.joolemarketplace.service;

import com.itlize.joolemarketplace.model.*;

import java.util.List;
import java.util.Optional;

public interface TechnicalDetailService {
    TechnicalDetail createTechnicalDetail(TechnicalDetail technicalDetail);
    Optional<TechnicalDetail> getTechnicalDetailById(Integer TechnicalDetailId);
    Optional<TechnicalDetail> getTechnicalDetailByProduct(Product product);
    List<TechnicalDetail> getAllTechnicalDetails();
    TechnicalDetail updateTechnicalDetail(TechnicalDetail technicalDetail);
    void deleteTechnicalDetail(Integer technicalDetailId);
}
