package io.github.joyoungc.jpa.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductTest {

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void createProduct() throws Exception {

		ProductDTO.Create createDto = new ProductDTO.Create();
		createDto.setProductName("asdf");
		createDto.setPrice(10000);

		mockMvc.perform(
				post("/products").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(createDto)))
				.andDo(print()).andExpect(status().isCreated())
		// .andExpect(jsonPath("",))
		;
	}

}
