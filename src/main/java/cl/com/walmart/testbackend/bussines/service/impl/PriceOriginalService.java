package cl.com.walmart.testbackend.bussines.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.com.walmart.testbackend.bussines.model.ProductInfo;
import cl.com.walmart.testbackend.bussines.service.IPriceFormaterService;
import cl.com.walmart.testbackend.persistence.model.Product;
import cl.com.walmart.testbackend.util.Formatter;

@Service(value = "priceOriginalService")
public class PriceOriginalService implements IPriceFormaterService {

	@Autowired
	private Formatter formatter;

	@Override
	public Page<Product> format(Page<Product> page, Pageable pageable) {
		List<Product> products = new ArrayList<>();
		for (Product product : page.getContent()) {
			products.add( createProductInfo(product));

		}
		return new PageImpl<>(products, pageable, page.getTotalElements());
	}

	private Product createProductInfo(Product product) {
		return new ProductInfo.Builder().
				brand(product.getBrand()).
				description(product.getDescription()).
				originalPrice(formatter.formatAmount(product.getPrice(),0)).
				price(product.getPrice()).
				id(product.getId()).
				image(product.getImage())
				.build();
	}

	@Override
	public List<Product> format(List<Product> products) {
		List<Product> list = new ArrayList<>();
		for (Product product :products) {
			list.add( createProductInfo(product));
		}
		return list;
	}


}
