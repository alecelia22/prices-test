package com.ecommerce.prices.repositories;

import com.ecommerce.prices.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public interface PriceRepository extends JpaRepository<Price, Long> {

    Stream<Price> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(Long brandId, Long productId, LocalDateTime start, LocalDateTime end);
}

