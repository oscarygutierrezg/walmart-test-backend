package cl.com.walmart.testbackend.web.dto;


public class ProductDto {
	
	private String text;
	private int page;
	private  int size;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "ProductDto [text=" + text + ", page=" + page + ", size=" + size + "]";
	}
	
	
	
	
}
