package com.ecommerce.entities.concretes;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ecommerce.core.entities.abstracts.AbstractBaseEntity;
import com.ecommerce.core.entities.concretes.User;
import com.ecommerce.entities.enums.OrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "order")
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "orderDetails" })
public class Order extends AbstractBaseEntity {
    @Column(name = "orderDate", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date orderDate;

    @Column(name = "status", nullable = false)
    private OrderStatusEnum status;

    @Column(name = "deliveryDate", nullable = true)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date deliveryDate;

    @Column(name = "totalPrice", nullable = false)
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;
}
