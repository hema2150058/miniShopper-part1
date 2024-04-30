package com.mini.shopper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.shopper.repo.BillingRepo;
import com.mini.shopper.repo.CartRepo;
import com.mini.shopper.repo.OrderRepo;
import com.mini.shopper.repo.OrderedProductRepo;

@Service
public class OrderService {

	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	OrderedProductRepo orderProductRepo;
	
	@Autowired
	BillingRepo billingRepo;
	
	@Autowired
	OrderRepo orderRepo;
	
	
}
