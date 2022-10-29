package com.youbuysell.sell.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.youbuysell.sell.exception.ProductNotFoundException;
import com.youbuysell.sell.model.Product;
import com.youbuysell.sell.repository.SellRepo;

@Service
public class SellServiceImpl implements SellService {
	
	@Autowired
	SellRepo sellrepo;

	@Override
	public Product addProduct(Product productnew) {
		Optional<Product> addprod=sellrepo.findById(productnew.getProductid());
		if(addprod!=null)
		
			sellrepo.save(productnew);
			return productnew;
		
		

	}

	@Override
	public List<Product> viewproducts() {
		return sellrepo.findAll();
	}

	@Override
	public List<Product> getproductbysid(String sid) {
		List<Product> sprod = sellrepo.findProductBySellerid(sid);
		return sprod;

	}

	@Override
	public boolean deleteproduct(String pid) throws ProductNotFoundException {
		Optional<Product> product=sellrepo.findById(pid);
		if(product.isPresent())
		{
			sellrepo.deleteById(pid);
			return true;
		}
		else
			throw new ProductNotFoundException("Product not available");
	}
	}


