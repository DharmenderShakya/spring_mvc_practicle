package com.srping_mvc_object_mapper_practicle.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.srping_mvc_object_mapper_practicle.entity.Order;
import com.srping_mvc_object_mapper_practicle.entity.Product;
import com.srping_mvc_object_mapper_practicle.repository.CustomerRepository;
import com.srping_mvc_object_mapper_practicle.repository.OrderRepository;
import com.srping_mvc_object_mapper_practicle.repository.ProductRepository;
import com.srping_mvc_object_mapper_practicle.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
	
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product updated) {
        Product p = getById(id);
        p.setName(updated.getName());
        p.setPrice(updated.getPrice());
        p.setDescription(updated.getDescription());
        p.setQuantityInStock(updated.getQuantityInStock());
        return productRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
    
    
}
