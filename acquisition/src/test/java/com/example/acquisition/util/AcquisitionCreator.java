package com.example.acquisition.util;

import com.example.acquisition.model.Acquisition;
import org.springframework.beans.factory.annotation.Autowired;

public class AcquisitionCreator {

    public static Acquisition createAcquisitionToSaved() {
        return Acquisition.builder()
                .value(1123.0)
                .user(null)
                .property(PropertyCreator.createPropertyToSaved())
                .build();
    }

    public static Acquisition createValidAcquisition() {
        return Acquisition.builder()
                .value(1111.0)
                .user(UserCreator.createUserToSaved())
                .property(PropertyCreator.createPropertyToTest1())
                .build();
    }

    public static Acquisition createValidUpdateAcquisition() {
        return Acquisition.builder()
                .value(1121.0)
                .user(null)
                .property(null)
                .build();
    }

}
