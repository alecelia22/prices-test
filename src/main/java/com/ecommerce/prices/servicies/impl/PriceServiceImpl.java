package com.ecommerce.prices.servicies.impl;

import com.ecommerce.prices.dtos.GetPriceResponse;
import com.ecommerce.prices.entities.Price;
import com.ecommerce.prices.repositories.PriceRepository;
import com.ecommerce.prices.servicies.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Transactional
    @Override
    public Optional<GetPriceResponse> getPriceByDateAndProductAndBrand(LocalDateTime date, Long productId, Long brandId) {
        Optional<Price> optionalPrice = priceRepository.
                // Based on the test requirement:
                // I'm assuming that there is no change of having 2 prices with the same priority for the same period of time.
                findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId, productId, date, date)
                .findFirst();

        return optionalPrice.map(this::convertPriceToGetPriceResponse);
    }

    private GetPriceResponse convertPriceToGetPriceResponse(Price price) {
        return GetPriceResponse.builder()
                .productId(price.getProduct().getId())
                .brandId(price.getBrand().getId())
                .priceList(price.getPriceList())
                .from(price.getStartDate())
                .to(price.getEndDate())
                .price(price.getFinalPrice())
                .priceCurrency(price.getCurrency())
                .build();
    }
}
