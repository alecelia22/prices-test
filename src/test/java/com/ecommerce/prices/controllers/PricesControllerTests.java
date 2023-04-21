package com.ecommerce.prices.controllers;

import com.ecommerce.prices.dtos.GetPriceResponse;
import com.ecommerce.prices.servicies.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PricesControllerTests {

    private final PriceService priceService = mock(PriceService.class);

    private final PricesController underTest;

    public PricesControllerTests() {
        underTest = new PricesController(priceService);
    }

    @Test
    void getPrice() {
        LocalDateTime date = LocalDateTime.now();
        Long productId = RandomGenerator.getDefault().nextLong();
        Long brandId = RandomGenerator.getDefault().nextLong();
        Optional<GetPriceResponse> expected = Optional.of(GetPriceResponse.builder().build());
        when(priceService.getPriceByDateAndProductAndBrand(date, productId, brandId)).thenReturn(expected);

        ResponseEntity<GetPriceResponse> actual = underTest.getPrice(date, productId, brandId);

        verify(priceService, times(1)).getPriceByDateAndProductAndBrand(date, productId, brandId);
        assertEquals(HttpStatusCode.valueOf(200), actual.getStatusCode());
        assertEquals(expected.get(), actual.getBody());
    }

    @Test
    void getPrice_notFound() {
        when(priceService.getPriceByDateAndProductAndBrand(any(), any(), any())).thenReturn(Optional.empty());

        ResponseEntity<GetPriceResponse> actual = underTest.getPrice(LocalDateTime.now(), 1L, 1L);

        verify(priceService, times(1)).getPriceByDateAndProductAndBrand(any(), any(), any());
        assertEquals(HttpStatusCode.valueOf(404), actual.getStatusCode());
    }
}
