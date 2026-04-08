package com.srping_mvc_object_mapper_practicle.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.srping_mvc_object_mapper_practicle.entity.Order;
import com.srping_mvc_object_mapper_practicle.entity.Product;
import com.srping_mvc_object_mapper_practicle.exception.ResourceNotFoundException;
import com.srping_mvc_object_mapper_practicle.repository.CustomerRepository;
import com.srping_mvc_object_mapper_practicle.repository.OrderRepository;
import com.srping_mvc_object_mapper_practicle.repository.ProductRepository;
import com.srping_mvc_object_mapper_practicle.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    
    @Override
    public Order placeOrder(Order order) {

        double total = order.getProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();

        order.setTotalPrice(total);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("PLACED");

        return orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Not Found"));
    }
}
