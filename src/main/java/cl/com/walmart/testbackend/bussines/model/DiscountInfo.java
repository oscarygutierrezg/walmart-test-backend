package cl.com.walmart.testbackend.bussines.model;

public class DiscountInfo {
	
	private String percentage;
	private String originalPrice;
	private String priceWithDiscount;
	
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public String getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getPriceWithDiscount() {
		return priceWithDiscount;
	}
	public void setPriceWithDiscount(String priceWithDiscount) {
		this.priceWithDiscount = priceWithDiscount;
	}
	
	

}
