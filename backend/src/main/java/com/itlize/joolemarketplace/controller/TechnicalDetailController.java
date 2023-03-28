package com.itlize.joolemarketplace.controller;

import com.itlize.joolemarketplace.model.TechnicalDetail;
import com.itlize.joolemarketplace.service.TechnicalDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/technicalDetail")
public class TechnicalDetailController {
    @Autowired
    TechnicalDetailService technicalDetailService;

    /*@PostMapping
    publi ResponseEntity<?> createTechnicalDetail(@RequestBody TechnicalDetail technicalDetail) {
        try{
            TechnicalDetail createdtechnitalDetail
        }
        catch (RuntimeException e) {

        }
    }**/
}