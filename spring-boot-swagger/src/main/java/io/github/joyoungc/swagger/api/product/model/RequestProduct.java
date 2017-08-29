package io.github.joyoungc.swagger.api.product.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.joyoungc.common.Code;
import io.github.joyoungc.common.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = Code.Constants.PRODUCT + "요청")
public class RequestProduct extends Product {

	@JsonIgnore
	public String getProductId() {
		return super.getProductId();
	}

	@ApiModelProperty(value = Code.Constants.PRODUCT_NAME, required = true, example = "Sample Product", position = Code.Constants.PRODUCT_NAME_POS)
	@NotBlank
	public String getProductName() {
		return super.getProductName();
	}

	@ApiModelProperty(value = Code.Constants.PRICE, required = true, example = "10000", position = Code.Constants.PRICE_POS)
	@NotNull
	public Integer getPrice() {
		return super.getPrice();
	}

	@ApiModelProperty(value = Code.Constants.PRODUCT_DESC, required = false, example = "상세설명", position = Code.Constants.PRODUCT_DESC_POS)
	public String getDescription() {
		return super.getDescription();
	}

}
