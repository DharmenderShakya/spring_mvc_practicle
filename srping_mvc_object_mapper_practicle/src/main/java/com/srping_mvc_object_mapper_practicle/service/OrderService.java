package com.srping_mvc_object_mapper_practicle.service;

import java.util.List;

import com.srping_mvc_object_mapper_practicle.entity.Order;
import com.srping_mvc_object_mapper_practicle.entity.Product;

public interface OrderService {

	public Order placeOrder(Order order);

    public Order getById(Long id);

}
