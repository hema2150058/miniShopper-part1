package com.mini.shopper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mini.shopper.dto.OrderDetailsRes;
import com.mini.shopper.dto.PlaceOrderReq;
import com.mini.shopper.dto.PlaceOrderRes;
import com.mini.shopper.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@PostMapping("/placeOrder") //Customer
	public ResponseEntity<PlaceOrderRes> placeOrder(@RequestBody PlaceOrderReq placeorderreq) {
		PlaceOrderRes placeorderres = orderService.placeOrder(placeorderreq);
		return new ResponseEntity<>(placeorderres,HttpStatus.CREATED);
	}	
	
	@GetMapping("getOrderDetails/{orderNumber}") //Customer
	public ResponseEntity<OrderDetailsRes> getOrderDetails(@PathVariable long orderNumber) {
		OrderDetailsRes orderdetails = orderService.getOrderDetails(orderNumber);
		return ResponseEntity.ok(orderdetails);
	}
	
	@GetMapping("/purchaseHistory/{userId}") //Customer
	public ResponseEntity<List<OrderDetailsRes>> getPurchaseHistory(@PathVariable String userId) {
		List<OrderDetailsRes> orderDtos = orderService.getPurchaseHistory(userId);
		return ResponseEntity.ok(orderDtos);
	}
	
	@GetMapping("/orderHistory")
	public ResponseEntity<List<OrderDetailsRes>> getAllOrders() {
		List<OrderDetailsRes> orderDtos = orderService.getAllOrders();
		return ResponseEntity.ok(orderDtos);
	}
	
	@GetMapping("/pendingOrders")
	public ResponseEntity<List<OrderDetailsRes>> getAllPendingOrders() {
		List<OrderDetailsRes> orderDtos = orderService.getAllPendingOrders("Pending");
		return ResponseEntity.ok(orderDtos);
	}

}

