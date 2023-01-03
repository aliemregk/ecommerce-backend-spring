package com.ecommerce.dataaccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entities.concretes.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> getAllByUserId(int id);
}
