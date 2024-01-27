package com.coffeeshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "orders")
@Getter
@Setter
@Accessors(chain = true)
public class OrderEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDateTime orderTime;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private CategoryEntity category;

    @ManyToOne
    private UserEntity employee;

}
