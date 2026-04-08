package com.srping_mvc_json_view_practicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.srping_mvc_json_view_practicle.entity.Order;
import com.srping_mvc_json_view_practicle.request.OrderRequest;
import com.srping_mvc_json_view_practicle.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/getAllOrder")
	public List<Order> getAllOrder() {
		return orderService.getAllOrder();
	}
	
	@PostMapping("/createOrder")
	public Order createOrder(@RequestBody OrderRequest order) {
		return orderService.create(order);
	}
	
	
}
