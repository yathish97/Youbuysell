package com.youbuysell.sell.service;

import java.util.List;

import com.youbuysell.sell.exception.ProductNotFoundException;
import com.youbuysell.sell.model.Product;

public interface SellService {
	
	Product addProduct(Product productnew);
	List<Product> viewproducts();
	List<Product> getproductbysid(String sid);
	boolean deleteproduct(String pid)throws ProductNotFoundException;


}
