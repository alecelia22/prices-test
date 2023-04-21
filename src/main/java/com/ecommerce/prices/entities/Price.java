package com.ecommerce.prices.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRICES")
public class Price {

    @Id
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer priceList;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private int priority;

    @Column(name = "PRICE")
    private BigDecimal finalPrice;

    @Column(name = "CURR")
    private String currency;
}
