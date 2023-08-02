package com.ingexample.storemgmt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingexample.storemgmt.controller.ProductController;
import com.ingexample.storemgmt.entity.Product;
import com.ingexample.storemgmt.helpers.CustomErrorResponse;
import com.ingexample.storemgmt.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class StoreMgmtApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {

	}

	@Test
	public void shouldReturnInfoMessageWhenServerIsUp() throws Exception {
		this.mockMvc.perform(get("/api/products/info"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Store Products API is up and running!")));
	}

	@Test
	public void getProductsShouldReturnListOfProducts() throws Exception {
		when(productService.getProducts()).thenReturn(List.of(new Product("Telefon", 500d, 42, LocalDate.of(2026, 10, 10)),
				new Product("Casa", 20000d, 20, LocalDate.of(2056, 10, 10))));

		MvcResult result = this.mockMvc.perform(get("/api/products"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		List<Product> productList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
		assertEquals(2, productList.size());
		assertEquals("Telefon", productList.get(0).getName());
		assertEquals("Casa", productList.get(1).getName());
	}

	@Test
	public void getProductsEmptyListShouldReturn404andError() throws Exception {
		when(productService.getProducts()).thenReturn(List.of());

		MvcResult result = this.mockMvc.perform(get("/api/products"))
				.andDo(print())
				.andExpect(status().is(404))
				.andReturn();

		CustomErrorResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), CustomErrorResponse.class);
		assertEquals("No items found.", response.getMessage());
		assertEquals("404", response.getStatus());
	}

	@Test
	public void whenGetProductsThrowsErrorEndpointShouldReturn400andErrorMessage() throws Exception{
		when(productService.getProducts()).thenAnswer(invocationOnMock -> { throw new Exception("This is a test Exception."); });

		MvcResult result = this.mockMvc.perform(get("/api/products"))
				.andDo(print())
				.andExpect(status().is(400))
				.andReturn();

		CustomErrorResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), CustomErrorResponse.class);
		assertEquals("This is a test Exception.", response.getMessage());
		assertEquals("400", response.getStatus());
	}

}
