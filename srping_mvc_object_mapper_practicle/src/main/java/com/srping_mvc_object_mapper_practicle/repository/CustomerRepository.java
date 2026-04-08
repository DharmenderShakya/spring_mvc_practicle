package com.srping_mvc_object_mapper_practicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srping_mvc_object_mapper_practicle.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
