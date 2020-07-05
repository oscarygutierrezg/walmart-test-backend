package cl.com.walmart.testbackend.persistence.service;

import java.util.List;

import org.springframework.data.domain.Page;

import cl.com.walmart.testbackend.persistence.model.Product;

public interface IProductService {
    
    List<Product> findProductById(int id);
    
    Page<Product> findProductByText(String text, int page, int size);
    
    Page<Product> findProductByBrandOrDescription(String text, int page, int size);
   
}
