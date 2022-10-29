package com.Youbuysell.Cart.controller;

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

import com.Youbuysell.Cart.exception.ProductAlreadyExistsException;
import com.Youbuysell.Cart.exception.ProductIdNotFoundException;
import com.Youbuysell.Cart.model.ProductItem;
import com.Youbuysell.Cart.service.CartService;

@RestController
@CrossOrigin
@RequestMapping("/Cart")
public class CartController {
	@Autowired
	CartService cartserve;
	@PostMapping("/saveproduct")
	public ResponseEntity addproduct(@RequestBody ProductItem newitem)
	{
		try {
			ProductItem item =cartserve.addproduct(newitem);
			return new ResponseEntity<ProductItem>(item,HttpStatus.CREATED);
		}catch(ProductAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}

	}
	@GetMapping("/viewbyseller/{sellerid}")
	public ResponseEntity<?> viewproduct(@PathVariable("sellerid") String sellerid)
	{
		
		List<ProductItem > items= cartserve.findBySellerid(sellerid);
		return new ResponseEntity<List>(items,HttpStatus.OK);
		
	}
	@GetMapping("/viewproduct/{buyerid}")
	public ResponseEntity<?> viewproducts(@PathVariable("buyerid") String product)
	{
		
		List<ProductItem > items= cartserve.findByBuyerid(product);
		return new ResponseEntity<List>(items,HttpStatus.OK);
		
	}
	
	@RequestMapping("/delete/{productid}")
	public ResponseEntity<?> delete(@PathVariable("productid") String id)
	{
		System.out.println(id);
		try {
			boolean deleteid=  cartserve.deleteitem(id);
			return new ResponseEntity<String>("item deleted successfully",HttpStatus.OK);
		} catch (ProductIdNotFoundException e) {
			
	 return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
}
