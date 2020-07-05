package cl.com.walmart.testbackend.bussines.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.com.walmart.testbackend.bussines.model.ProductDiscount;
import cl.com.walmart.testbackend.bussines.service.IPriceFormaterService;
import cl.com.walmart.testbackend.persistence.model.Product;
import cl.com.walmart.testbackend.util.Formatter;

@Service(value = "palindromeDiscountService")
public class PalindromeDiscountService implements IPriceFormaterService {

	@Autowired
	private Formatter formatter;

	@Override
	public Page<Product> format(Page<Product> page, Pageable pageable) {
		List<Product> products = new ArrayList<>();
		for (Product product : page.getContent()) {
			products.add(createProductInfo(product));
		}
		return new PageImpl<>(products, pageable, page.getTotalElements());
	}

	@Override
	public List<Product> format(List<Product> products) {
		List<Product> list = new ArrayList<>();
		for (Product product :products) {
			list.add(createProductInfo(product));
		}
		return list;
	}

	private Product createProductInfo(Product product) {
		return new ProductDiscount.Builder().
				brand(product.getBrand()).
				description(product.getDescription()).
				discountInfo(formatter.makeDiscountInfo(0.5, product.getPrice())).
				price(product.getPrice()).
				id(product.getId()).
				image(product.getImage())
				.build();
	}

   
}
