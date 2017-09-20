package io.github.joyoungc.swagger.api.product.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.github.joyoungc.common.Code;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class ProductDTO {
	
	@ApiModel(value = Code.Constants.PRODUCT + "요청")
	public static class Create {
		
		private String productName;
		private Integer price;
		private String description;
		
		@ApiModelProperty(value = Code.Constants.PRODUCT_NAME, required = true, example = "Sample Product", position = Code.Constants.PRODUCT_NAME_POS)
		@NotBlank
		public String getProductName() {
			return productName;
		}

		@ApiModelProperty(value = Code.Constants.PRICE, required = true, example = "10000", position = Code.Constants.PRICE_POS)
		@NotNull
		public Integer getPrice() {
			return price;
		}

		@ApiModelProperty(value = Code.Constants.PRODUCT_DESC, required = false, example = "상세설명", position = Code.Constants.PRODUCT_DESC_POS)
		public String getDescription() {
			return description;
		}
		
	}

}
