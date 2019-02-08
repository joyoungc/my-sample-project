package io.github.joyoungc.swagger.api.product.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.joyoungc.common.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

public class ProductDTO {

    @Data
    @ApiModel(value = Constants.PRODUCT + "등록")
    public static class Create {

        private String productName;
        private Integer price;
        private String description;

        @ApiModelProperty(value = Constants.PRODUCT_NAME, required = true, example = "Sample Product", position =
                Constants.PRODUCT_NAME_POS)
        @NotBlank
        public String getProductName() {
            return productName;
        }

        @ApiModelProperty(value = Constants.PRICE, required = true, example = "10000", position = Constants.PRICE_POS)
        @NotNull
        public Integer getPrice() {
            return price;
        }

        @ApiModelProperty(value = Constants.PRODUCT_DESC, required = false, example = "상세설명", position =
                Constants.PRODUCT_DESC_POS)
        public String getDescription() {
            return description;
        }

    }

    @Data
    @ApiModel(value = Constants.PRODUCT + "수정")
    public static class Update {

        @JsonIgnore
        private String productId;
        private String productName;
        private Integer price;
        private String description;

        @ApiModelProperty(value = Constants.PRODUCT_NAME, required = true, example = "Sample Product", position =
                Constants.PRODUCT_NAME_POS)
        @NotBlank
        public String getProductName() {
            return productName;
        }

        @ApiModelProperty(value = Constants.PRICE, required = true, example = "10000", position = Constants.PRICE_POS)
        @NotNull
        public Integer getPrice() {
            return price;
        }

        @ApiModelProperty(value = Constants.PRODUCT_DESC, required = false, example = "상세설명", position =
                Constants.PRODUCT_DESC_POS)
        public String getDescription() {
            return description;
        }

    }

    @Data
    @ApiModel(value = Constants.PRODUCT)
    public static class Response {

        private String productId;
        private String productName;
        private Integer price;
        private String description;

        @ApiModelProperty(value = Constants.PRODUCT_ID, required = true, example = "P100003", position =
                Constants.PRODUCT_ID_POS)
        public String getProductId() {
            return productId;
        }

        @ApiModelProperty(value = Constants.PRODUCT_NAME, required = true, example = "LG G6", position =
                Constants.PRODUCT_NAME_POS)
        public String getProductName() {
            return productName;
        }

        @ApiModelProperty(value = Constants.PRICE, required = true, example = "540000", position = Constants.PRICE_POS)
        public Integer getPrice() {
            return price;
        }

        @ApiModelProperty(value = Constants.PRODUCT_DESC, required = false, example = "상세설명", position = Constants.PRODUCT_DESC_POS)
        public String getDescription() {
            return description;
        }

    }

}