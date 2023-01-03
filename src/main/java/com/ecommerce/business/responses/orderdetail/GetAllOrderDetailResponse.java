package com.ecommerce.business.responses.orderdetail;

import com.ecommerce.entities.concretes.Order;
import com.ecommerce.entities.concretes.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllOrderDetailResponse {
    private int id;
    private Product product;
    private Order order;
    private int quantity;
}
