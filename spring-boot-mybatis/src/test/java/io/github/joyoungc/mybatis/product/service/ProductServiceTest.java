package io.github.joyoungc.mybatis.product.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.joyoungc.common.model.Product;
import io.github.joyoungc.mybatis.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    public void test1CreateProduct() {
        Product product = Product.builder().productId("test").productName("테스트").price(10000).description("설명").build();
        int result = productService.createProduct(product);
        assertThat(result, greaterThan(0));
    }

    @Test
    public void test2GetProduct() {
        assertThat(productService.getProduct("test"), notNullValue());
    }

    @Test
    public void test3SelectProducts() {
        assertThat(productService.selectProducts().size(), greaterThan(0));
    }

    @Test
    public void test4UpdateProduct() {
        String updateName = "테스트수정";
        Product product =
                Product.builder().productId("test").productName(updateName).price(20000).description("설명수정").build();
        productService.updateProduct(product);
        Product result = productService.getProduct("test");
        assertThat(result.getProductName(), is("테스트수정"));
    }

    @Test
    public void test5DeleteProduct() {
        assertThat(productService.deleteProduct("test"), is(1));
    }

}
