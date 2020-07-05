package cl.com.walmart.testbackend.persistence.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3067614957534744689L;
	
	@Id
	private  ObjectId  _id;
	protected int id;
	protected String brand;
	protected String description;
	protected String image;
	protected int price;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String get_id() {
		return _id.toHexString(); 
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	@Override
	public String toString() {
		return "Product [_id=" + _id + ", id=" + id + ", brand=" + brand + ", description=" + description + ", image="
				+ image + ", price=" + price + "]";
	}
	
	
}
