package com.Youbuysell.Cart.service;

import java.util.List;

import com.Youbuysell.Cart.exception.ProductAlreadyExistsException;
import com.Youbuysell.Cart.exception.ProductIdNotFoundException;
import com.Youbuysell.Cart.model.ProductItem;

public interface CartService {
	ProductItem addproduct(ProductItem item) throws ProductAlreadyExistsException;
	List<ProductItem> findByBuyerid(String buyerid);
	List<ProductItem> findBySellerid(String sellerid);
	boolean deleteitem(String productid) throws ProductIdNotFoundException;

}

 
