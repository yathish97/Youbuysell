package com.youbuysell.sell.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.youbuysell.sell.exception.ProductNotFoundException;
import com.youbuysell.sell.model.Product;
import com.youbuysell.sell.repository.SellRepo;
import com.youbuysell.sell.service.SellServiceImpl;

public class SellServiceImplTest {
	 @Mock
	 SellRepo repo;
		Product product;
		
		 @InjectMocks
		 SellServiceImpl service;
		
		
	    List<Product> productList ;
	    Optional<Product> options;
	  
	    @Before
		public void setUp()
		{
			MockitoAnnotations.initMocks(this);
			
		    product =new Product();
		    
		    product.setProducttitle("RoyalOak");
		    product.setSellerid("vaishnavi@gmail.com");
		    product.setProductid("12");
		    product.setPrice("5000");
		    product.setProductdescription("Solid Wood Solid Wood 4 Seater Dining Set  (Finish Color -Brown, Knock Down)");
		    product.setProductimage("https://tse1.mm.bing.net/th?id=OIP.DHIrIDJg0vqI8XDkZa28JAHaE9&pid=Api&rs=1&c=1&qlt=95&w=146&h=98");
		    product.setCategory("homeappliances");
		    productList = new ArrayList<>();
		    productList.add(product);
		    options = Optional.of(product);
		   
	    }
	   
	    @Test
	    public void addproductSuccess() {
	        when(repo.insert(product)).thenReturn(product);
	        Product productSaved = service.addProduct(product);
	        assertEquals(product, productSaved);
	    }
	  
	 
	    @Test
	    public void deleteProductSuccess() throws ProductNotFoundException {
	        when(repo.findById(product.getProductid())).thenReturn(options);
	        boolean flag = service.deleteproduct(product.getProductid());
	        assertEquals(true, flag);
	    }
	    
	    @Test
	    public void getAllNewsByUserId() {
	        when(repo.findProductBySellerid(product.getSellerid())).thenReturn(productList);
	        List<Product> productlist1 = service.getproductbysid(product.getSellerid());
	        assertEquals(productList, productlist1);
	    }


}
