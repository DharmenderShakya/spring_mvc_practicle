package com.srping_mvc_object_mapper_practicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srping_mvc_object_mapper_practicle.entity.Order;
import com.srping_mvc_object_mapper_practicle.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private ObjectMapper objectMapper;

    //  Place Order
    @PostMapping
    public String placeOrder(@RequestBody String json) throws Exception {

        Order order = objectMapper.readValue(json, Order.class);

        Order saved = service.placeOrder(order);

        return objectMapper.writeValueAsString(saved);
    }

    //  Get Order
    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id) throws Exception {

        Order order = service.getById(id);

        return objectMapper.writeValueAsString(order);
    }
}
