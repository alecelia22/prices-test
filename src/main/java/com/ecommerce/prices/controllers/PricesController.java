package com.ecommerce.prices.controllers;


import com.ecommerce.prices.dtos.GetPriceResponse;
import com.ecommerce.prices.servicies.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PricesController {

    private final PriceService priceService;

    @GetMapping("/price")
    public ResponseEntity<GetPriceResponse> getPrice(
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "brandId") Long brandId) {
       Optional<GetPriceResponse> optionalResponse = priceService.getPriceByDateAndProductAndBrand(date, productId, brandId);

       return optionalResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
