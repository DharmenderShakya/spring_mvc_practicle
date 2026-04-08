package com.srping_mvc_object_mapper_practicle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.srping_mvc_object_mapper_practicle.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srping_mvc_object_mapper_practicle.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ObjectMapper objectMapper;

    // Get All Products
    @GetMapping
    public String getAll() throws Exception {
        List<Product> products = service.getAll();
        return objectMapper.writeValueAsString(products);
    }

    //  Get Product By ID
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id) throws Exception {
        Product product = service.getById(id);
        return objectMapper.writeValueAsString(product);
    }

    // Create Product
    @PostMapping
    public String create(@RequestBody String json) throws Exception {

        Product product = objectMapper.readValue(json, Product.class);

        Product saved = service.save(product);

        return objectMapper.writeValueAsString(saved);
    }

    //  Update Product
    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestBody String json) throws Exception {

        Product product = objectMapper.readValue(json, Product.class);

        Product updated = service.update(id, product);

        return objectMapper.writeValueAsString(updated);
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws Exception {

        service.delete(id);

        return objectMapper.writeValueAsString("Deleted Successfully");
    }
}
