package io.github.joyoungc.swagger.api.product.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.joyoungc.common.Code;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = Code.Constants.PRODUCT + "요청")
public class RequestProduct {

	@JsonIgnore
	private String productId;

	@NotBlank
	private String productName;

	@NotNull
	private Integer price;

	private String description;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@ApiModelProperty(value = Code.Constants.PRODUCT_NAME, required = true, example = "Sample Product", position = Code.Constants.PRODUCT_NAME_POS)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@ApiModelProperty(value = Code.Constants.PRICE, required = true, example = "10000", position = Code.Constants.PRICE_POS)
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
