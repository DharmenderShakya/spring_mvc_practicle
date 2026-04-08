package com.srping_mvc_json_view_practicle.service;

import java.util.List;

import com.srping_mvc_json_view_practicle.entity.Order;
import com.srping_mvc_json_view_practicle.request.OrderRequest;

public interface OrderService {

	public List<Order> getAllOrder();
	
	public Order create(OrderRequest order);
}
