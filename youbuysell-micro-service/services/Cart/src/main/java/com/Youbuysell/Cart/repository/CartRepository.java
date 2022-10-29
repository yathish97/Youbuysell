package com.Youbuysell.Cart.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Youbuysell.Cart.model.ProductItem;
@Repository
public interface CartRepository extends MongoRepository<ProductItem,String> {
	
	List<ProductItem> findByBuyerid(String buyerid);
	List<ProductItem> findBySellerid(String sellerid);
	
}
