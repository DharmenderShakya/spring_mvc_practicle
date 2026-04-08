package com.srping_mvc_object_mapper_practicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srping_mvc_object_mapper_practicle.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
