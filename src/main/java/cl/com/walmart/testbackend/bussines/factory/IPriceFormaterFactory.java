package cl.com.walmart.testbackend.bussines.factory;

import cl.com.walmart.testbackend.bussines.service.IPriceFormaterService;

public interface IPriceFormaterFactory {
	
	IPriceFormaterService createPriceFormater(String text);

}
