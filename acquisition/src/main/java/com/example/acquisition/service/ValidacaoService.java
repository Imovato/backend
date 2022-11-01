package com.example.acquisition.service;

import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;

public interface ValidacaoService {

    void validar(User user, Property property);
	//Double calculaParteDoLucroPraImobiliaria(AcquisitionDTO acquisiton);
	//Double calcularValorIPTU(AcquisitionDTO acquisition);
}
