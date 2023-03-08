package com.ecommerce.dataaccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entities.concretes.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    List<OrderDetail> getAllByOrderId(int id);
}
