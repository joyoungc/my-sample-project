package io.github.joyoungc.jpa.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.joyoungc.jpa.product.model.ProductDTO;
import io.github.joyoungc.jpa.product.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductTest {

    private MockMvc mockMvc;

    private static boolean isInit = true;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    public ProductService productService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        ProductDTO.Create createDto = new ProductDTO.Create();
        if (isInit) {
            for (int i = 0; i < 31; i++) {
                createDto.setProductName("ipsum lorem-" + (i + 1));
                createDto.setPrice((i + 1) * 1000);
                createDto.setDescription("설명 - " + (i + 1));
                productService.createProduct(createDto);
            }
            isInit = false;
        }

    }

    @Ignore
    public void createProduct() throws Exception {

        ProductDTO.Create createDto = new ProductDTO.Create();
        createDto.setProductName("create test");
        createDto.setPrice(10000);

        mockMvc.perform(
                post("/products").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(createDto)))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void selectProducts() throws Exception {
        mockMvc.perform(get("/products?page=4&size=10"))
                .andDo(print())
                .andExpect(status().isOk())
                // .andExpect(jsonPath("$.content.[0].productId").value("31"));
                // .andExpect(jsonPath("$.content").isEmpty());
                .andExpect(jsonPath("$.content.length()").value(1));
    }

    @Ignore
    public void getProduct() throws Exception {
        mockMvc.perform(get("/products/1")).andDo(print()).andExpect(status().isOk());
    }

    @Ignore
    public void updateProduct() throws Exception {

        ProductDTO.Update updateDto = new ProductDTO.Update();
        updateDto.setProductName("update test");
        updateDto.setPrice(58200);
        updateDto.setDescription("업데이트 설명이다능!!");

        mockMvc.perform(put("/products/12").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(updateDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("update test"));
    }

    @Ignore
    public void deleteProduct() throws Exception {
        mockMvc.perform(delete("/products/3"))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/products/3"))
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist());

    }

}
