package io.github.joyoungc.swagger.api.product.model;

import io.github.joyoungc.common.Code;
import io.github.joyoungc.common.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value=Code.Constants.PRODUCT)
public class ResponseProduct extends Product {
	
	public ResponseProduct() {		
	}
	
	public ResponseProduct(Product product) {
		super.setProductId(product.getProductId());
		super.setProductName(product.getProductName());
		super.setPrice(product.getPrice());
		super.setDescription(product.getDescription());
	}
	
	@ApiModelProperty(value = Code.Constants.PRODUCT_ID, required = true, example = "P100003" , position = Code.Constants.PRODUCT_ID_POS)		
	public String getProductId() {
		return super.getProductId();
	}
	
	@ApiModelProperty(value = Code.Constants.PRODUCT_NAME, required = true, example = "LG G6", position = Code.Constants.PRODUCT_NAME_POS)
	public String getProductName() {
		return super.getProductName();
	}
	
	@ApiModelProperty(value = Code.Constants.PRICE, required = true, example = "540000", position = Code.Constants.PRICE_POS)
	public Integer getPrice() {
		return super.getPrice();
	}
	
	@ApiModelProperty(value = Code.Constants.PRODUCT_DESC, required = false, example = "상세설명", position = Code.Constants.PRODUCT_DESC_POS)
	public String getDescription() {
		return super.getDescription();
	}
	
}
