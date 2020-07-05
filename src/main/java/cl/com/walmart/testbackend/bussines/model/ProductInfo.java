package cl.com.walmart.testbackend.bussines.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cl.com.walmart.testbackend.persistence.model.Product;

@JsonIgnoreProperties(value = {"_id"})
public class ProductInfo extends Product{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2769762741631186746L;
	private String originalPrice;
	
	public String getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	
	private ProductInfo(Builder builder) {
		this.brand = builder.brand;
		this.id = builder.id;
		this.price = builder.price;
		this.description = builder.description;
		this.image = builder.image;
		setOriginalPrice(builder.originalPrice);
	}

	public static class Builder {
		private String originalPrice;
		private String image;
		private String description;
		private int price;
		private int id;
		private String brand;

		public Builder originalPrice(String originalPrice) {
			this.originalPrice = originalPrice;
			return this;
		}
		public Builder image(String image) {
			this.image = image;
			return this;
		}
		public Builder description(String description) {
			this.description = description;
			return this;
		}
		public Builder brand(String brand) {
			this.brand = brand;
			return this;
		}
		public Builder price(int price) {
			this.price = price;
			return this;
		}
		public Builder id(int id) {
			this.id = id;
			return this;
		}
		public ProductInfo build() {
            return new ProductInfo(this);
        }
	}
}
