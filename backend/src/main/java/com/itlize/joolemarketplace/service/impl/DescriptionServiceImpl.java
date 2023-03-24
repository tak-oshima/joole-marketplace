package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.model.Description;
import com.itlize.joolemarketplace.model.Product;
import com.itlize.joolemarketplace.repository.DescriptionRepository;
import com.itlize.joolemarketplace.repository.ProductRepository;
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
        if (descriptionRepository.findById(description.getDescriptionId()).isPresent()) {
            throw new RuntimeException(
                    String.format("Create Description Exception: description with description_id \"%d\" already exists", description.getDescriptionId())
            );
        }
        retrun descriptionRepository.save(description);
    }

    @Override
    public Optional<Description> getDescriptionById(Integer descriptionId) {
        return descriptionRepository.findById(descriptionId);
    }
    public Optional<Description> getDescriptionById(Integer descriptionId) {
        return descriptionRepository.findById(descriptionId);
    }
    public Optional<Description> getDescriptionByProduct(Product product) {
        return descriptionRepository.findByProduct(product)
    }
    public List<Description> getAllDescription() {
        return descriptionRepository.findAll();
    }
    @Override
    public Description updateDescription(Description description) {
        if (!descriptionRepository.findById(description.getDescriptionId()).isPresent()) {
            throw new RuntimeException(
                    String.format("Update Description Exception: description with description_id \"%d\" not found", description.getDescriptionId())
            );
        }
        return descriptionRepository.save(description);
    }
    @Override
    public void deleteDescription(Description description) {
        if (!descriptionRepository.findById(description.getDescriptionId()).isPresent()) {
            throw new RuntimeException(
                    String.format("Delete Description Exception: description with description_id \"%d\" not found", description.getDescriptionId())
            );
        }
        descriptionRepository.deleteById(description.getDescriptionId());
    }
}