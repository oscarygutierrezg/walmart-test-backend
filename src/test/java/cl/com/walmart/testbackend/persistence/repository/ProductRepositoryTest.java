package cl.com.walmart.testbackend.persistence.repository;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

import cl.com.walmart.testbackend.TestbackendApplication;
import cl.com.walmart.testbackend.persistence.model.Product;


@SpringBootTest
@ContextConfiguration(classes = TestbackendApplication.class)
class ProductRepositoryTest {


	@Autowired
	private ProductRepository productRepository;

	@Test
	public void whenInsertingProduct_thenProductIsInserted() {
		final Product p = new Product();
		p.setId(1000000);
		Product pNew = productRepository.insert(p);
		assertTrue(productRepository.findProductById(1).size()==1);
		productRepository.delete(pNew);

	}
	
	@Test
	public void givenProductsExist_whenFindingAllProductsWithPagination_thenProductsAreFoundAndOrderedOnPage() {


		final Pageable pageableRequest = PageRequest.of(0, 3);

		final Page<Product> page = productRepository.findProductByText("adda", pageableRequest);
		List<Product> Products = page.getContent();

		assertEquals(3, Products.size());
		assertEquals(3, page.getTotalPages());
	}
	
	@Test
	public void given1ProductsExist_whenFindingAllProductsWithPagination_thenProductsAreFoundAndOrderedOnPage() {
		
		
		final Pageable pageableRequest = PageRequest.of(0, 3);
		
		final Page<Product> page = productRepository.findProductByBrandOrDescription("lxi", pageableRequest);
		List<Product> Products = page.getContent();
		

		assertEquals(3, Products.size());
		assertEquals(4, page.getTotalPages());
	}

}
