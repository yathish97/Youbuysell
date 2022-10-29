package com.youbuysell.sell.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youbuysell.sell.exception.ProductNotFoundException;
import com.youbuysell.sell.model.Product;
import com.youbuysell.sell.service.SellService;

@RestController
@CrossOrigin
public class SellController {
	
	@Autowired
	SellService sellservice;
	
	@PostMapping("/Sell/product/addproduct")
	public ResponseEntity<?> addproduct(@RequestBody Product addprod)
	{
		Product product=sellservice.addProduct(addprod);
		
				return new ResponseEntity(product,HttpStatus.CREATED);
	
	}
	
	@GetMapping("/Sell/product/viewproducts")
	public ResponseEntity<?> viewiproducts()
	{
		List<Product> products= sellservice.viewproducts();
		return new ResponseEntity<List>(products,HttpStatus.OK);
		
	}
	
	@GetMapping("/Sell/product/viewbysid/{sellerid}")
	public ResponseEntity<?> getproductbysid(@PathVariable("sellerid")String sid)
	{
		List<Product> product = sellservice.getproductbysid(sid);
		return new ResponseEntity<List>(product,HttpStatus.OK);
	}
	
	@DeleteMapping("/Sell/delete/{productid}")
	public ResponseEntity<?> delete(@PathVariable("productid") String id)
	{
		try {
			boolean deleteid= sellservice.deleteproduct(id);
			return new ResponseEntity<String>("Product deleted successfully",HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			
	 return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}



}
