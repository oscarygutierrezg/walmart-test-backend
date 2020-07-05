package cl.com.walmart.testbackend.persistence.service;

import org.springframework.data.domain.Page;

import cl.com.walmart.testbackend.persistence.model.Product;

public interface IProductService {
    
	Page<Product> findProductById(int id, int page, int size);
    
    Page<Product> findProductByText(String text, int page, int size);
    
    Page<Product> findProductByBrandOrDescription(String text, int page, int size);
   
}
