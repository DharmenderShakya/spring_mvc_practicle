package com.srping_mvc_json_view_practicle.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srping_mvc_json_view_practicle.entity.Order;
import com.srping_mvc_json_view_practicle.entity.User;
import com.srping_mvc_json_view_practicle.repository.OrderRepository;
import com.srping_mvc_json_view_practicle.repository.UserRepository;
import com.srping_mvc_json_view_practicle.request.OrderRequest;
import com.srping_mvc_json_view_practicle.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Order> getAllOrder() {
		return orderRepository.findAll();
	}

	@Override
	public Order create(OrderRequest dto) {

        //  Validate user
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        //  Create order
        Order order = new Order();
        order.setProduct(dto.getProduct());
        order.setTotal(dto.getTotal());
        order.setStatus(dto.getStatus());
        order.setUser(user);

        return orderRepository.save(order);
    }

}
