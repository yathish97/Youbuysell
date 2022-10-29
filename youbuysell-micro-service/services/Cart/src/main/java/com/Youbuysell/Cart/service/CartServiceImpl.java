package com.Youbuysell.Cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Youbuysell.Cart.exception.ProductAlreadyExistsException;
import com.Youbuysell.Cart.exception.ProductIdNotFoundException;
import com.Youbuysell.Cart.model.ProductItem;
import com.Youbuysell.Cart.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartRepository cartrepo;

	@Override
	public ProductItem addproduct(ProductItem item) throws ProductAlreadyExistsException {
		Optional<ProductItem> product =cartrepo.findById(item.getProductid());
		if(product.isPresent()) {
			throw new ProductAlreadyExistsException("product is present in cart");
		}
		else {
			cartrepo.save(item);
			return item;
		}
	}


	@Override
	public List<ProductItem> findByBuyerid(String buyerid) {
		List<ProductItem> item=cartrepo.findByBuyerid(buyerid);
		return item;
		}
	@Override
	public List<ProductItem> findBySellerid(String sellerid) {
		List<ProductItem> item=cartrepo.findBySellerid(sellerid);
		return item;
	}

	@Override
	public boolean deleteitem(String productid) throws ProductIdNotFoundException {
		Optional<ProductItem> item=cartrepo.findById(productid);
		if(item.isPresent())
		{
			cartrepo.deleteById(productid);
			return true;
		}
		else
			throw new ProductIdNotFoundException("Item doesnot exist");
	}




}
