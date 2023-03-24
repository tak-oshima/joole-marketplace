package com.itlize.joolemarketplace.service;

import com.itlize.joolemarketplace.model.*;

import java.util.List;
import java.util.Optional;

public interface DescriptionService {
    Description createDescription(Description description);
    Optional<Description> getDescriptionById(Integer descriptionId);
    Optional<Description> getDescriptionByProduct(Product product);
    List<Description> getAllDescriptions();

    Description updateDescription(Description description);
    void deleteDescription(Description description);
}