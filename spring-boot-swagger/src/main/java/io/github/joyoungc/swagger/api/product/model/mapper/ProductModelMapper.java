package io.github.joyoungc.swagger.api.product.model.mapper;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.google.common.collect.Lists;

import io.github.joyoungc.common.model.Product;
import io.github.joyoungc.swagger.api.product.model.ResponseProduct;

public class ProductModelMapper {

	public static ResponseProduct toResponse(Product source) {
		ResponseProduct target = new ResponseProduct();
		BeanUtils.copyProperties(source, target);
		return target;
	}

	public static List<ResponseProduct> toResponse(List<Product> source) {
		List<ResponseProduct> target = Lists.newArrayList();
		if (source != null) {
			for (Product product : source) {
				target.add(toResponse(product));
			}
		}
		return target;
	}

}
