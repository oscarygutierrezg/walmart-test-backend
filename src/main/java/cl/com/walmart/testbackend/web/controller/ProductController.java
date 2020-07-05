package cl.com.walmart.testbackend.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.com.walmart.testbackend.bussines.factory.IPriceFormaterFactory;
import cl.com.walmart.testbackend.exception.MyResourceNotFoundException;
import cl.com.walmart.testbackend.persistence.model.Product;
import cl.com.walmart.testbackend.persistence.service.IProductService;
import cl.com.walmart.testbackend.web.dto.ProductDto;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private IPriceFormaterFactory priceFormaterFactory;
	@Autowired
	private IProductService productService;


	@GetMapping("/{id}")
	public List<Product> findProductById(@PathVariable("id") int id) {
		return priceFormaterFactory.createPriceFormater(""+id).format(productService.findProductById(id));
	}


	@PostMapping("findByText")
	public Page<Product> findByText(@RequestBody ProductDto dto) {
		Page<Product> page = productService.findProductByText(dto.getText(), dto.getPage(), dto.getSize());
		if(page.getContent().isEmpty()) {
			page = productService.findProductByBrandOrDescription(dto.getText(), dto.getPage(), dto.getSize());
		}
		if (dto.getPage() > page.getTotalPages()) {
			throw new MyResourceNotFoundException();
		}
		return priceFormaterFactory.createPriceFormater(dto.getText()).format(page,PageRequest.of( dto.getPage(), dto.getSize()));

	}

}
