package com.youbuysell.sell.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.youbuysell.sell.model.Product;

@Repository
public interface SellRepo extends MongoRepository<Product,String> {
	
	List<Product>findProductBySellerid(String sellerid);
	


}
