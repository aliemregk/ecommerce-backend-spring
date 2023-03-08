package com.ecommerce.dataaccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.concretes.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> getAllByUserId(int id);
}
