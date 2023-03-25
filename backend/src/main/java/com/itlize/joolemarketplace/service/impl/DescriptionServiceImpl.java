package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.DescriptionNotFoundException;
import com.itlize.joolemarketplace.model.Description;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.repository.DescriptionRepository;
import com.itlize.joolemarketplace.service.DescriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DescriptionServiceImpl implements DescriptionService {
    @Autowired
    private DescriptionRepository descriptionRepository;

    @Override
    public Description createDescription(Description description) {
        return descriptionRepository.save(description);
    }

    @Override
    public Optional<Description> getDescriptionById(Integer descriptionId) {
        return descriptionRepository.findById(descriptionId);
    }

    @Override
    public Optional<Description> getDescriptionByProduct(Product product) {
        return descriptionRepository.findByProduct(product);
    }

    @Override
    public List<Description> getAllDescriptions() {
        return descriptionRepository.findAll();
    }

    @Override
    public Description updateDescription(Description description) {
        if (!descriptionRepository.findById(description.getDescriptionId()).isPresent()) {
            throw new DescriptionNotFoundException(description.getDescriptionId());
        }
        return descriptionRepository.save(description);
    }

    @Override
    public void deleteDescription(Integer descriptionId) {
        if (!descriptionRepository.findById(descriptionId).isPresent()) {
            throw new DescriptionNotFoundException(descriptionId);
        }
        descriptionRepository.deleteById(descriptionId);
    }
}
