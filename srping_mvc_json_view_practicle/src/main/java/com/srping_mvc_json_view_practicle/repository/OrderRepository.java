package com.srping_mvc_json_view_practicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srping_mvc_json_view_practicle.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
