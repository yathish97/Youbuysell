package com.youbuysell.sell.controllertest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youbuysell.sell.controller.SellController;
import com.youbuysell.sell.exception.ProductNotFoundException;
import com.youbuysell.sell.model.Product;
import com.youbuysell.sell.service.SellService;




@RunWith(SpringRunner.class)
@WebMvcTest
public class SellControllerTest {
	
	 @Autowired
		MockMvc mockmvc;
		
		@InjectMocks
		SellController sellcontroller;
	    
		@MockBean
		SellService sellservice;
		
		Product product;

		@Before
		public void setUp()
		{
			MockitoAnnotations.initMocks(this);
			mockmvc=MockMvcBuilders.standaloneSetup(sellcontroller).build();
		    product =new Product();
		    product.setProductid("123");
		    product.setSellerid("vaishnavi@gmail.com");;
		    product.setProducttitle("SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s");
		    product.setProductdescription("Easy upgrade for faster boot up, shutdown, application load and response (As compared to 5400 RPM SATA 2.5” hard drive; Based on published specifications and internal benchmarking tests using PCMark vantage scores) Boosts burst write performance");
		    product.setProductimage("https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_.jpg");
		    product.setCategory("electronics");
		    product.setPrice("5000");
		  
		  
		    
	    }
		
		@Test
		public void whenaddthenstoreproductsuccess() throws Exception
		{
		Mockito.when(sellservice.addProduct(product)).thenReturn(product);
		mockmvc.perform(MockMvcRequestBuilders.post("/product/addproduct")
											    .contentType(MediaType.APPLICATION_JSON)
												.content(convertObjToJson(product)))
												.andExpect(MockMvcResultMatchers.status().isCreated())
												.andDo(MockMvcResultHandlers.print());							
		}
		private String convertObjToJson(Object obj) throws JsonProcessingException,Exception {
			ObjectMapper objectMapper=new ObjectMapper();
			String op=objectMapper.writeValueAsString(obj);
			return op;
		}
		
		

		
		@Test
		public void testviewAllproduct() throws Exception
		{
			mockmvc.perform(MockMvcRequestBuilders.get("/product/viewproducts"))
			.andExpect(MockMvcResultMatchers.status().isOk());
		}
		
		@Test
		public void testviewProductById() throws Exception 
		{
			mockmvc.perform(MockMvcRequestBuilders.get("/product/viewbysid/vaishnavi@gmail.com"))
			.andExpect(MockMvcResultMatchers.status().isOk());
		}
		
		 @Test
		    public void deleteProductSuccess() throws Exception {
			 Mockito.when(sellservice.deleteproduct("123")).thenReturn(true);
		        mockmvc.perform(MockMvcRequestBuilders.delete("/delete/123")
		                .contentType(MediaType.APPLICATION_JSON))
		                .andExpect(MockMvcResultMatchers.status().isOk())
		                .andDo(MockMvcResultHandlers.print());

		    }


		    @Test
		    public void deleteProductFailure() throws Exception {
		    	Mockito.when(sellservice.deleteproduct("123")).thenThrow(ProductNotFoundException .class);
		        mockmvc.perform(MockMvcRequestBuilders.delete("/delete/123")
		                .contentType(MediaType.APPLICATION_JSON))
		                .andExpect(MockMvcResultMatchers.status().isNotFound())
		                .andDo(MockMvcResultHandlers.print());

		    }

		

}
