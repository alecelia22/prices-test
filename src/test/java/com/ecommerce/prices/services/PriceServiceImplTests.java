package com.ecommerce.prices.services;

import com.ecommerce.prices.dtos.GetPriceResponse;
import com.ecommerce.prices.entities.Brand;
import com.ecommerce.prices.entities.Price;
import com.ecommerce.prices.entities.Product;
import com.ecommerce.prices.repositories.PriceRepository;
import com.ecommerce.prices.servicies.impl.PriceServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.random.RandomGenerator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PriceServiceImplTests {

    private final PriceRepository priceRepository = mock(PriceRepository.class);

    private final PriceServiceImpl underTest;

    public PriceServiceImplTests() {
        underTest = new PriceServiceImpl(priceRepository);
    }

    @Test
    void getPriceByDateAndProductAndBrand() {
        LocalDateTime date = LocalDateTime.now();
        Product product = new Product();
        Brand brand = new Brand();
        BigDecimal finalPrice = BigDecimal.valueOf(43.21);
        Integer priceList = RandomGenerator.getDefault().nextInt();
        Price expected = new Price();
        expected.setStartDate(date);
        expected.setEndDate(date);
        expected.setProduct(product);
        expected.setBrand(brand);
        expected.setFinalPrice(finalPrice);
        expected.setPriceList(priceList);

        when(priceRepository
            .findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brand.getId(),
                product.getId(),
                date,
                date))
                    .thenReturn(Stream.of(expected));

        Optional<GetPriceResponse> actual = underTest.getPriceByDateAndProductAndBrand(date, product.getId(), brand.getId());

        verify(priceRepository, times(1))
            .findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                brand.getId(),
                product.getId(),
                date,
                date);
        assertTrue(actual.isPresent());
        GetPriceResponse getPriceResponse = actual.get();
        assertEquals(expected.getBrand().getId(), getPriceResponse.getBrandId());
        assertEquals(expected.getProduct().getId(), getPriceResponse.getProductId());
        assertEquals(expected.getStartDate(), getPriceResponse.getFrom());
        assertEquals(expected.getEndDate(), getPriceResponse.getTo());
        assertEquals(expected.getFinalPrice(), getPriceResponse.getPrice());
        assertEquals(expected.getPriceList(), getPriceResponse.getPriceList());
    }

    @Test
    void getPriceByDateAndProductAndBrand_returnEmptyValue() {
        when(priceRepository
            .findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(any(), any(), any(), any()))
                .thenReturn(Stream.empty());

        Optional<GetPriceResponse> actual = underTest.getPriceByDateAndProductAndBrand(LocalDateTime.now(), 1L, 2L);

        assertTrue(actual.isEmpty());
    }
}
