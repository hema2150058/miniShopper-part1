package com.mini.shopper.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mini.shopper.model.Cart;
import com.mini.shopper.model.Product;

public interface CartRepo extends JpaRepository<Cart, Integer> {

	List<Cart> findByCartId(int cartId);
	
	Cart findByProductIdAndUserId(Product product,int userId);
	
	void deleteByProductIdAndUserId(Product product,int userId);
	
	void deleteAllByUserId(int userId);
	
	boolean deleteById(int id);
	
	List<Cart> findByUserId(int userId);
}
