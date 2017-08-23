package io.github.joyoungc.swagger.api.product.model;

import io.github.joyoungc.common.Code;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value=Code.Constants.PRODUCT)
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

	@ApiModelProperty(value = Code.Constants.PRODUCT_ID, required = true, example = "P100003" , position = Code.Constants.PRODUCT_ID_POS)		
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@ApiModelProperty(value = Code.Constants.PRODUCT_NAME, required = true, example = "LG G6", position = Code.Constants.PRODUCT_NAME_POS)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@ApiModelProperty(value = Code.Constants.PRICE, required = true, example = "540000", position = Code.Constants.PRICE_POS)
	public int getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@ApiModelProperty(value = Code.Constants.PRODUCT_DESC, required = false, example = "상세설명", position = Code.Constants.PRODUCT_DESC_POS)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
