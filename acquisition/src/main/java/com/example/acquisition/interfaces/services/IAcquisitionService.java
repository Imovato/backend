package com.example.acquisition.interfaces.services;

import java.util.List;
import com.example.acquisition.model.Acquisition;
import com.example.acquisition.model.Property;
import com.example.acquisition.model.User;

public interface IAcquisitionService{
	void saveAcquisition(Acquisition acquisition);
	List<Acquisition> findAllAcquisitionsByUser(User user);
    Acquisition findAcquisitionByProperty(Property property);

	/*Boolean validarCpf(User user);
	Boolean validaRenda(User user, Property property);
	Double calculaParteDoLucroPraImobiliaria(AcquisitionDTO acquisiton);
	Double calcularValorIPTU(AcquisitionDTO acquisition);*/
	//void calculaSeguro(Acquisition acquisition);

	//void calculaSeguro(Acquisition acquisition);
	//void validaRenda(User user);
	//Boolean possuiFiador(User user);


}
