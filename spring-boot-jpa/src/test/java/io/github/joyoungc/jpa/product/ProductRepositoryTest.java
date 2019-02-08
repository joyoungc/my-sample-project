package io.github.joyoungc.jpa.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.joyoungc.jpa.product.domain.Product;
import io.github.joyoungc.jpa.product.repository.ProductRepository;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void test1ProductSaveAndFindAll() {

        LocalDateTime today = LocalDateTime.now();

        for (int i = 0; i < 33; i++) {
            Product product = new Product();
            product.setProductName("테스트" + (i + 1));
            product.setPrice(i * ThreadLocalRandom.current().nextInt(1, 1000000));
            product.setCreateDate(today);
            repository.save(product);
        }

        List<Product> products1 = repository.findAll();

        assertThat(products1).isNotNull().hasSize(33);

        Page<Product> products2 = repository.findAll(PageRequest.of(0, 10));

        assertThat(products2.getTotalElements()).isEqualTo(33);
        assertThat(products2.getTotalPages()).isEqualTo(4);
        assertThat(products2.getNumberOfElements()).isEqualTo(10);

        Page<Product> products3 = repository.findAll(PageRequest.of(3, 10));

        products3.forEach(System.out::println);

        assertThat(products3.getNumberOfElements()).isEqualTo(3);

    }

    @Test
    public void test2ProductSelectListByPage() {
        // products.getTotalElements()
    }

}
