
package com.Youbuysell.Cart.service;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.Youbuysell.Cart.exception.ProductAlreadyExistsException;
import com.Youbuysell.Cart.exception.ProductIdNotFoundException;
import com.Youbuysell.Cart.model.ProductItem;
import com.Youbuysell.Cart.repository.CartRepository;

public class CartServiceImplTest {
	 @Mock
	 CartRepository repo;
		ProductItem product;
		
		 @InjectMocks
		 CartServiceImpl service;
		
		

	    List<ProductItem> buyerList ;
	    Optional<ProductItem> options;
	   
	    @Before
		public void setUp()
		{
			MockitoAnnotations.initMocks(this);
			
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
		    buyerList = new ArrayList<>();
		    buyerList.add(product);
		    options = Optional.of(product);
		    
	    }
	    
	    @Test
	    public void addproductSuccess() throws ProductAlreadyExistsException {
	        when(repo.insert(product)).thenReturn(product);
	        ProductItem productSaved = service.addproduct(product);
	        assertEquals(product, productSaved);

	    }
	   

	  

	    @Test
	    public void deleteProductSuccess() throws ProductIdNotFoundException {
	        when(repo.findById(product.getProductid())).thenReturn(options);
	        boolean flag = service.deleteitem(product.getProductid());
	        assertEquals(true, flag);

	    }
}