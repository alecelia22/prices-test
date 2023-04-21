package com.ecommerce.prices.servicies;

import com.ecommerce.prices.dtos.GetPriceResponse;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {

    Optional<GetPriceResponse> getPriceByDateAndProductAndBrand(LocalDateTime date, Long productId, Long brandId);
}
