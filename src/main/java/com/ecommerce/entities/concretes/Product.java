package com.ecommerce.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "product")
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    @NotNull(message = "Name is required.")
    @NotBlank(message = "Name is required.")
    private String name;

    @Column(name = "description", nullable = false)
    @NotNull(message = "Description is required.")
    @NotBlank(message = "Description is required.")
    private String description;

    @Column(name = "stock", nullable = false)
    @NotNull(message = "Stock quantity is required.")
    @Min(value = 0)
    private int stock;

    @Column(name = "unitPrice", nullable = false)
    @NotNull(message = "Unit price is required.")
    @Min(value = 0)
    private double unitPrice;

    @Column(name = "discount")
    @Max(value = 99)
    @Min(value = 0)
    private int discount;
}
