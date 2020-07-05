package cl.com.walmart.testbackend.persistence.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import cl.com.walmart.testbackend.persistence.service.IProductService;
import cl.com.walmart.testbackend.TestbackendApplication;
import cl.com.walmart.testbackend.persistence.model.Product;
import cl.com.walmart.testbackend.persistence.repository.ProductRepository;

import static org.mockito.Mockito.when;


@SpringBootTest
@ContextConfiguration(classes = TestbackendApplication.class)
class ProductServiceTest {

    @Autowired
    private IProductService productService;
    
    @MockBean
    private ProductRepository dao;
    
    
    @Test()
    public void testFindProductByText() throws Exception {
        Product p = new  Product();
        p.setBrand("Test");
        p.setDescription("Test");
        p.setImage("Test");
        p.setId(1);
        p.setPrice(1);
    	Page<Product> page = new PageImpl<>(Collections.singletonList(p));
        when(dao.findProductByText(ArgumentMatchers.any(String.class), ArgumentMatchers.any(Pageable.class))).thenReturn(page);
        assertFalse(productService.findProductByText("Test", 0, 1).isEmpty());
    }
    @Test()
    public void testFindProductByBrandOrDescription() throws Exception {
    	Product p = new  Product();
    	p.setBrand("Test");
    	p.setDescription("Test");
    	p.setImage("Test");
    	p.setId(1);
    	p.setPrice(1);
    	Page<Product> page = new PageImpl<>(Collections.singletonList(p));
    	when(dao.findProductByBrandOrDescription(ArgumentMatchers.any(String.class), ArgumentMatchers.any(Pageable.class))).thenReturn(page);
    	assertFalse(productService.findProductByBrandOrDescription("Test", 0, 1).isEmpty());
    }
    
    @Test()
    public void testFindProductById() throws Exception {
    	Product p = new  Product();
    	p.setBrand("Test");
    	p.setDescription("Test");
    	p.setImage("Test");
    	p.setId(1);
    	p.setPrice(1);
    	Page<Product> page = new PageImpl<>(Collections.singletonList(p));
    	when(dao.findProductById(ArgumentMatchers.any(Integer.class), ArgumentMatchers.any(Pageable.class))).thenReturn(page);
    	assertFalse(productService.findProductById(1, 0, 1).isEmpty());
    }

}
