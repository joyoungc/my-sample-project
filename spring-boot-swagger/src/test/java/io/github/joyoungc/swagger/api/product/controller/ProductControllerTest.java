package io.github.joyoungc.swagger.api.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.joyoungc.swagger.api.product.model.ProductDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
//@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@AutoConfigureRestDocs
public class ProductControllerTest {

    //@Rule
    //public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    /*
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(ProductController.class).apply(documentationConfiguration
        (restDocumentation)).build();
    }
    */

    FieldDescriptor[] product = new FieldDescriptor[]{
            fieldWithPath("productId").description("상품아이디"),
            fieldWithPath("productName").description("상품명"),
            //fieldWithPath("description").description("상품설명"),
            fieldWithPath("price").description("상품 가격").type(JsonFieldType.NUMBER)};


    @Test
    public void testGetProduct() throws Exception {
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/mobile/products/{productId}", "P100001"))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("get-product",
                        pathParameters(parameterWithName("productId").description("상품 아이디")),
                        responseFields(product)
                ));
        //            .andExpect(content().string(containsString("Hello World")))
    }


    @Test
    public void testSelectProducts() throws Exception {
        this.mockMvc.perform(get("/mobile/products?page=2&size=100"))
                .andExpect(status().isOk())
                .andDo(document("select-product",
                        requestParameters(
                                parameterWithName("page").description("The page to retrieve"),
                                parameterWithName("size").description("Size of the page to retrieve")),
                        responseFields(
                                subsectionWithPath("[]").description("<<resources-product-one, 상품>> 의 배열")
                        ))
                );
    }

    @Test
    public void createProduct() throws Exception {
        ProductDTO.Create createDto = new ProductDTO.Create();
        createDto.setProductName("create test");
        createDto.setPrice(10000);

        mockMvc.perform(
                post("/mobile/products").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(createDto)))
                .andDo(print()).andExpect(status().isCreated()).andDo(document("create-product",
                requestFields(
                        // fieldWithPath("productId").description("The title of the note").type(JsonFieldType.STRING),
                        fieldWithPath("productName").description("상품명").type(JsonFieldType.STRING),
                        fieldWithPath("description").description("상품설명(optional)").type(JsonFieldType.STRING).optional(),
                        fieldWithPath("price").description("상품 가격").type(JsonFieldType.NUMBER))
        ));

    }

    @Test
    public void updateProduct() {
    }

    @Test
    public void deleteProduct() {
    }
}