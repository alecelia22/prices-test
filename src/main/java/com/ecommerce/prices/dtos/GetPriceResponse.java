package com.ecommerce.prices.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class GetPriceResponse {

    private long productId;

    private long brandId;

    private int priceList;

    private LocalDateTime from;

    private LocalDateTime to;

    private BigDecimal price;

    private String priceCurrency;
}
