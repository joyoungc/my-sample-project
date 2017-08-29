package io.github.joyoungc.common.model;

public class Product {
	
	private String productId;
	private String productName;
	private Integer price;
	private String description;
	
	public Product() {}
	
	public Product(String productId, String productName, Integer price, String description) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.description = description;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price
				+ ", description=" + description + "]";
	}
	
}
