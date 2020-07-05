package cl.com.walmart.testbackend.web.controller;


import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.com.walmart.testbackend.TestbackendApplication;
import cl.com.walmart.testbackend.bussines.model.ProductDiscount;
import cl.com.walmart.testbackend.bussines.model.ProductInfo;
import cl.com.walmart.testbackend.persistence.model.Product;
import cl.com.walmart.testbackend.persistence.service.IProductService;
import cl.com.walmart.testbackend.util.Formatter;
import cl.com.walmart.testbackend.web.dto.ProductDto;


@SpringBootTest
@ContextConfiguration(classes = TestbackendApplication.class)
class ProductControllerTest {


	private String PRODUCTS_ENDPOINT = "/product/";

	private MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;


	@Autowired
	private Formatter formatter;

	@BeforeEach
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}


	@MockBean
	private IProductService productService;

	public Product createProduct() {
		Product p = new  Product();
		p.setBrand("adda");
		p.setDescription("oftetv sxgwl");
		p.setImage("www.lider.cl/catalogo/images/tvIcon.svg");
		p.setId(2305);
		p.setPrice(775684);
		return p;
	}

	@Test
	public void findProductById() throws Exception {
		String uri = PRODUCTS_ENDPOINT+"/23";
		when(productService.findProductById(ArgumentMatchers.any(Integer.class))).thenReturn(Collections.singletonList(createProduct()));
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, createProductJson());
	}
	@Test
	public void findProductByIdPalin() throws Exception {
		String uri = PRODUCTS_ENDPOINT+"/232";
		when(productService.findProductById(ArgumentMatchers.any(Integer.class))).thenReturn(Collections.singletonList(createProduct()));
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, createProductDiscountJson());
	}
	
	@Test
	public void findByText404() throws Exception {

		String uri = PRODUCTS_ENDPOINT+"/findByText";
		ProductDto product = new ProductDto();
		product.setPage(10);
		product.setSize(1);
		product.setText("Test");
		when(
				productService.findProductByText(
						ArgumentMatchers.any(String.class),
						ArgumentMatchers.any(Integer.class),
						ArgumentMatchers.any(Integer.class))).
		thenReturn(
				new PageImpl<>(Collections.singletonList(createProduct()))
				);
		when(
				productService.findProductByBrandOrDescription(
						ArgumentMatchers.any(String.class),
						ArgumentMatchers.any(Integer.class),
						ArgumentMatchers.any(Integer.class))).
		thenReturn(
				new PageImpl<>(new ArrayList<Product>())
				);	

		String inputJson = serializeObject(product);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}
	


	@Test
	public void findByText() throws Exception {

		String uri = PRODUCTS_ENDPOINT+"/findByText";
		ProductDto product = new ProductDto();
		product.setPage(0);
		product.setSize(1);
		product.setText("Test");
		when(
				productService.findProductByText(
						ArgumentMatchers.any(String.class),
						ArgumentMatchers.any(Integer.class),
						ArgumentMatchers.any(Integer.class))).
		thenReturn(
				new PageImpl<>(Collections.singletonList(createProduct()))
				);
		when(
				productService.findProductByBrandOrDescription(
						ArgumentMatchers.any(String.class),
						ArgumentMatchers.any(Integer.class),
						ArgumentMatchers.any(Integer.class))).
		thenReturn(
				new PageImpl<>(new ArrayList<Product>())
				);	

		String inputJson = serializeObject(product);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(deserializeProductList(content), createProductJson());
	}
	
	@Test
	public void findByTextPalin() throws Exception {

		String uri = PRODUCTS_ENDPOINT+"/findByText";
		ProductDto product = new ProductDto();
		product.setPage(0);
		product.setSize(1);
		product.setText("ana");
		when(
				productService.findProductByText(
						ArgumentMatchers.any(String.class),
						ArgumentMatchers.any(Integer.class),
						ArgumentMatchers.any(Integer.class))).
		thenReturn(
				new PageImpl<>(Collections.singletonList(createProduct()))
				);
		when(
				productService.findProductByBrandOrDescription(
						ArgumentMatchers.any(String.class),
						ArgumentMatchers.any(Integer.class),
						ArgumentMatchers.any(Integer.class))).
		thenReturn(
				new PageImpl<>(new ArrayList<Product>())
				);	

		String inputJson = serializeObject(product);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(deserializeProductList(content), createProductDiscountJson());
	}


	@Test
	public void findProductByBrandOrDescription() throws Exception {

		String uri = PRODUCTS_ENDPOINT+"/findByText";
		ProductDto product = new ProductDto();
		product.setPage(0);
		product.setSize(1);
		product.setText("Test");
		when(
				productService.findProductByText(
						ArgumentMatchers.any(String.class),
						ArgumentMatchers.any(Integer.class),
						ArgumentMatchers.any(Integer.class))).
		thenReturn(
				new PageImpl<>( new ArrayList<Product>())
				);
		when(
				productService.findProductByBrandOrDescription(
						ArgumentMatchers.any(String.class),
						ArgumentMatchers.any(Integer.class),
						ArgumentMatchers.any(Integer.class))).
		thenReturn(
				new PageImpl<>(Collections.singletonList(createProduct()))
				);	

		String inputJson = serializeObject(product);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(deserializeProductList(content), createProductJson());
	}
	
	
	@Test
	public void findProductByBrandOrDescriptionPalin() throws Exception {

		String uri = PRODUCTS_ENDPOINT+"/findByText";
		ProductDto product = new ProductDto();
		product.setPage(0);
		product.setSize(1);
		product.setText("ana");
		when(
				productService.findProductByText(
						ArgumentMatchers.any(String.class),
						ArgumentMatchers.any(Integer.class),
						ArgumentMatchers.any(Integer.class))).
		thenReturn(
				new PageImpl<>( new ArrayList<Product>())
				);
		when(
				productService.findProductByBrandOrDescription(
						ArgumentMatchers.any(String.class),
						ArgumentMatchers.any(Integer.class),
						ArgumentMatchers.any(Integer.class))).
		thenReturn(
				new PageImpl<>(Collections.singletonList(createProduct()))
				);	

		String inputJson = serializeObject(product);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(deserializeProductList(content), createProductDiscountJson());
	}


	private static String serializeObject(Object Product) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(Product);
	}

	private  String createProductJson() throws Exception {
		Product pr = createProduct();
		ProductInfo p = new ProductInfo.Builder().id(pr.getId()).
				brand( pr.getBrand()).
				description(pr.getDescription()).
				image(pr.getImage()).
				price(pr.getPrice()).
				originalPrice(formatter.formatAmount(pr.getPrice(),0)).build();
		return serializeObject(Collections.singletonList(p));
	}

	private  String createProductDiscountJson() throws Exception {

		Product pr = createProduct();
		ProductDiscount p = new ProductDiscount.Builder().id(pr.getId()).
				brand( pr.getBrand()).
				description(pr.getDescription()).
				image(pr.getImage()).
				price(pr.getPrice()).
				discountInfo(formatter.makeDiscountInfo(0.5, pr.getPrice())).build();
		return serializeObject(Collections.singletonList(p));
	}

	private  String deserializeProductList(String productJson) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JacksonJsonParser jsonParser = new JacksonJsonParser(mapper);
		Map map = jsonParser.parseMap(productJson);
		return serializeObject(map.get("content"));
	}



}
