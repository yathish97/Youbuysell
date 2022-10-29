package com.Youbuysell.Cart.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.Youbuysell.Cart.exception.ProductAlreadyExistsException;
import com.Youbuysell.Cart.exception.ProductIdNotFoundException;
import com.Youbuysell.Cart.model.ProductItem;
import com.Youbuysell.Cart.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest
public class CartControllerTest {

	   @Autowired
		MockMvc mockmvc;
		
		@InjectMocks
		CartController controller;
	    
		@MockBean
		CartService crtservice;
		
		ProductItem product;

		@Before
		public void setUp()
		{
			MockitoAnnotations.initMocks(this);
			mockmvc=MockMvcBuilders.standaloneSetup(controller).build();
		    product =new ProductItem();
		    product.setTransactionid("12yathish@gmail.com");
		    product.setBuyerid("yathish@gmail.com");
		    product.setTitle("RoyalOak");
		    product.setSellerid("pallu@gmail.com");
		    product.setProductid("12");
		    product.setPrice(5000);
		    product.setProductdiscription("Solid Wood Solid Wood 4 Seater Dining Set  (Finish Color -Brown, Knock Down)");
		    product.setImage("https://tse1.mm.bing.net/th?id=OIP.DHIrIDJg0vqI8XDkZa28JAHaE9&pid=Api&rs=1&c=1&qlt=95&w=146&h=98");
		    product.setDate(null);
		    product.setTime(null);
		    
	    }
		
		@Test
		public void whenaddthenstoreproductsuccess() throws Exception
		{
		Mockito.when(crtservice.addproduct(product)).thenReturn(product);
		mockmvc.perform(MockMvcRequestBuilders.post("/Cart/saveproduct")
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
		
//		 @Test
//		    public void  whenaddthenstoreproductFailure() throws Exception {
//
//			 Mockito.when(crtservice.addproduct(())).thenThrow(ProductAlreadyExistsException.class);
//		        mockmvc.perform(MockMvcRequestBuilders.post("/Cart/saveproduct")
//		                .contentType(MediaType.APPLICATION_JSON).content(convertObjToJson(product)))
//		                .andExpect(MockMvcResultMatchers.status().isConflict()).andDo(MockMvcResultHandlers.print());
//
//		    }

		
		
		
		@Test
		public void testviewAllproduct() throws Exception
		{
			mockmvc.perform(MockMvcRequestBuilders.get("/Cart/viewproduct/yathish@gmail.com"))
			.andExpect(MockMvcResultMatchers.status().isOk());
		}
		
		
		
		
		 @Test
		    public void deleteUserSuccess() throws Exception {
			 Mockito.when(crtservice.deleteitem("12")).thenReturn(true);
		        mockmvc.perform(MockMvcRequestBuilders.delete("/Cart/delete/12")
		                .contentType(MediaType.APPLICATION_JSON))
		                .andExpect(MockMvcResultMatchers.status().isOk())
		                .andDo(MockMvcResultHandlers.print());

		    }


		    @Test
		    public void deleteUserFailure() throws Exception {
		    	Mockito.when(crtservice.deleteitem("12")).thenThrow(ProductIdNotFoundException .class);
		        mockmvc.perform(MockMvcRequestBuilders.delete("/Cart/delete/12")
		                .contentType(MediaType.APPLICATION_JSON))
		                .andExpect(MockMvcResultMatchers.status().isNotFound())
		                .andDo(MockMvcResultHandlers.print());

		    }


		

}
