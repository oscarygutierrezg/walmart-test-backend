package cl.com.walmart.testbackend.bussines.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.com.walmart.testbackend.persistence.model.Product;

public interface IPriceFormaterService {
	
	Page<Product> format(final Page<Product> page, Pageable pageable);
}
