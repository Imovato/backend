package com.example.acquisition.util;

import com.example.acquisition.model.Acquisition;

public class AcquisitionPut {
    public static Acquisition createAcquisitionPutRequestBody() {
        return Acquisition.builder()
                .id(AcquisitionCreator.createValidUpdateAcquisition().getId())
                .property(AcquisitionCreator.createValidUpdateAcquisition().getProperty())
                .build();
    }
}
