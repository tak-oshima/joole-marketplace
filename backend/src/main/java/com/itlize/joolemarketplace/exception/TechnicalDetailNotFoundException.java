package com.itlize.joolemarketplace.exception;

import com.itlize.joolemarketplace.model.TechnicalDetail;

public class TechnicalDetailNotFoundException extends RuntimeException{
    public TechnicalDetailNotFoundException(Integer technicalDetailId) {
        super(String.format("A technical detail with technical_detail_id \"%d\" could not be found", technicalDetailId));
    }
}
