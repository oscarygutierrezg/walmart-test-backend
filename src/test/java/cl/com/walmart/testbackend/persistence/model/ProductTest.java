package cl.com.walmart.testbackend.persistence.model;

import static org.junit.jupiter.api.Assertions.*;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

class ProductTest {

	@Test
	void test() {
		String value = "Test";
		Product product = new Product();
		product.setBrand(value);
		assertFalse(product.getBrand().isEmpty());
	}
	@Test
	void test1() {
		String value = "Test";
		Product product = new Product();
		product.setDescription(value);;
		assertFalse(product.getDescription().isEmpty());
	}
	@Test
	void test2() {
		String value = "Test";
		Product product = new Product();
		product.setImage(value);
		assertFalse(product.getImage().isEmpty());
	}
	@Test
	void test3() {
		int value = 1;
		Product product = new Product();
		product.setId(value);;
		assertTrue(product.getId() == value);
	}
	@Test
	void test31() {
		int value = 1;
		Product product = new Product();
		product.setPrice(value);;
		assertTrue(product.getPrice() == value);
	}
	@Test
	void test4() {
		Product product = new Product();
		product.set_id(new ObjectId());
		assertNotNull(product.get_id());
	}
	@Test
	void test5() {
		String value = "Test";
		Product product = new Product();
		product.setBrand(value);
		product.set_id(new ObjectId());
		assertFalse(product.toString().isEmpty());
	}

}
