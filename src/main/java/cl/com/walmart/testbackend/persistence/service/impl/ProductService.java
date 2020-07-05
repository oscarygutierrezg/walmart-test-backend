package cl.com.walmart.testbackend.persistence.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.com.walmart.testbackend.persistence.model.Product;
import cl.com.walmart.testbackend.persistence.repository.ProductRepository;
import cl.com.walmart.testbackend.persistence.service.IProductService;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repository;

    public ProductService() {
        super();
    }

	@Override
	public Page<Product> findProductById(int id, int page, int size) {
		return repository.findProductById(id,PageRequest.of(page, size));
	}

	@Override
	public Page<Product> findProductByText(String text, int page, int size) {
		return repository.findProductByText(text,PageRequest.of(page, size));
	}

	@Override
	public Page<Product> findProductByBrandOrDescription(String text, int page, int size) {
		return repository.findProductByBrandOrDescription(text,PageRequest.of(page, size));
		
	}
}
