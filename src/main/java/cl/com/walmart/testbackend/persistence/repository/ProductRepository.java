package cl.com.walmart.testbackend.persistence.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import cl.com.walmart.testbackend.persistence.model.Product;

public interface ProductRepository  extends MongoRepository<Product, String> {

	@Query("{ 'id' : ?0 }")
	Page<Product> findProductById(int id, Pageable pageRequest);

    @Query("{ $text: { $search: ?0} }")
	Page<Product> findProductByText(String text, Pageable pageRequest);
    
    @Query("{$or: [{ brand:   { $regex:  ?0  }}, { description:   { $regex:  ?0  } }] }")
	Page<Product> findProductByBrandOrDescription(String text, Pageable pageRequest);
}
