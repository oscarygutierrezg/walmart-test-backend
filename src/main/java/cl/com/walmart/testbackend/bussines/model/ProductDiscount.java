package cl.com.walmart.testbackend.bussines.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cl.com.walmart.testbackend.persistence.model.Product;

@JsonIgnoreProperties(value = {"_id"})
public class ProductDiscount extends Product{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5369679977031848829L;

	private DiscountInfo discountInfo;


	private ProductDiscount(Builder builder) {
		this.brand = builder.brand;
		this.id = builder.id;
		this.price = builder.price;
		this.description = builder.description;
		this.image = builder.image;
		setDiscountInfo(builder.discountInfo);
	}

	public DiscountInfo getDiscountInfo() {
		return discountInfo;
	}

	public void setDiscountInfo(DiscountInfo discountInfo) {
		this.discountInfo = discountInfo;
	}

	public static class Builder {
		private DiscountInfo discountInfo;
		private String image;
		private String description;
		private int price;
		private int id;
		private String brand;

		public Builder discountInfo(DiscountInfo discountInfo) {
			this.discountInfo = discountInfo;
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
		public ProductDiscount build() {
            return new ProductDiscount(this);
        }
	}
}
