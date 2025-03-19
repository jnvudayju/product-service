package com.ud.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ud.productservice.dto.ProductRequest;
import com.ud.productservice.entity.Product;
import com.ud.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductserviceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:8.0.5");

	@DynamicPropertySource
	static void setMongoDBContainerProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

//	@Test
//	void contextLoads() {
//	}

	@Test
	void shouldCreateProduct() throws Exception {

		ProductRequest productRequest = getProductRequest();

		String productRequestString = objectMapper.writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
				.andExpect(status().isCreated());

        Assertions.assertEquals(1, productRepository.findAll().size());
	}

	@Test
	void shouldFetchAllProducts() throws Exception {
		// First, create and save a product
		ProductRequest productRequest = getProductRequest();
		productRepository.save(
				Product.builder()
						.name(productRequest.getName())
						.description(productRequest.getDescription())
						.price(productRequest.getPrice())
						.build()
		);

		// Perform GET request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product/get-all-product")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(result -> {
					String jsonResponse = result.getResponse().getContentAsString();
					// Optionally: Parse the response and check size or values
					Assertions.assertTrue(jsonResponse.contains("iphone 15"));
				});
	}

	private ProductRequest getProductRequest(){
		return ProductRequest
				.builder()
				.name("iphone 15")
				.description("iphone 15 launching today")
				.price(BigDecimal.valueOf(12000))
				.build();
	}



}
