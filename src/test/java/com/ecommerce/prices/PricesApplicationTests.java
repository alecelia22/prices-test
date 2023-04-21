package com.ecommerce.prices;

import com.ecommerce.prices.controllers.PricesController;
import com.ecommerce.prices.dtos.GetPriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PricesApplicationTests {

	@Autowired
	private PricesController underTest;

	private final Long PRODUCT_ID = 35455L;
	private final Long BRAND_ID = 1L;

	@Test
	void test1() {
		ResponseEntity<GetPriceResponse> response = underTest.getPrice(
				LocalDateTime.parse("2020-06-14T10:00:00"),
				PRODUCT_ID,
				BRAND_ID);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(1, response.getBody().getBrandId());
		assertEquals(PRODUCT_ID, response.getBody().getProductId());
		assertEquals(1, response.getBody().getPriceList());
		assertEquals(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP), response.getBody().getPrice());
		assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), response.getBody().getFrom());
		assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), response.getBody().getTo());
		assertEquals("EUR", response.getBody().getPriceCurrency());
	}

	@Test
	void test2() {
		ResponseEntity<GetPriceResponse> response = underTest.getPrice(
				LocalDateTime.parse("2020-06-14T16:00:00"),
				PRODUCT_ID,
				BRAND_ID);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(BRAND_ID, response.getBody().getBrandId());
		assertEquals(PRODUCT_ID, response.getBody().getProductId());
		assertEquals(2, response.getBody().getPriceList());
		assertEquals(BigDecimal.valueOf(25.45), response.getBody().getPrice());
		assertEquals(LocalDateTime.parse("2020-06-14T15:00:00"), response.getBody().getFrom());
		assertEquals(LocalDateTime.parse("2020-06-14T18:30:00"), response.getBody().getTo());
		assertEquals("EUR", response.getBody().getPriceCurrency());
	}

	@Test
	void test3() {
		ResponseEntity<GetPriceResponse> response = underTest.getPrice(
				LocalDateTime.parse("2020-06-14T21:00:00"),
				PRODUCT_ID,
				BRAND_ID);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(BRAND_ID, response.getBody().getBrandId());
		assertEquals(PRODUCT_ID, response.getBody().getProductId());
		assertEquals(1, response.getBody().getPriceList());
		assertEquals(BigDecimal.valueOf(35.50).setScale(2, RoundingMode.HALF_UP), response.getBody().getPrice());
		assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), response.getBody().getFrom());
		assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), response.getBody().getTo());
		assertEquals("EUR", response.getBody().getPriceCurrency());
	}

	@Test
	void test4() {
		ResponseEntity<GetPriceResponse> response = underTest.getPrice(
				LocalDateTime.parse("2020-06-15T10:00:00"),
				PRODUCT_ID,
				BRAND_ID);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(BRAND_ID, response.getBody().getBrandId());
		assertEquals(PRODUCT_ID, response.getBody().getProductId());
		assertEquals(3, response.getBody().getPriceList());
		assertEquals(BigDecimal.valueOf(30.50).setScale(2, RoundingMode.HALF_UP), response.getBody().getPrice());
		assertEquals(LocalDateTime.parse("2020-06-15T00:00:00"), response.getBody().getFrom());
		assertEquals(LocalDateTime.parse("2020-06-15T11:00:00"), response.getBody().getTo());
		assertEquals("EUR", response.getBody().getPriceCurrency());
	}

	@Test
	void test5() {
		ResponseEntity<GetPriceResponse> response = underTest.getPrice(
				LocalDateTime.parse("2020-06-16T21:00:00"),
				PRODUCT_ID,
				BRAND_ID);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(BRAND_ID, response.getBody().getBrandId());
		assertEquals(PRODUCT_ID, response.getBody().getProductId());
		assertEquals(4, response.getBody().getPriceList());
		assertEquals(BigDecimal.valueOf(38.95), response.getBody().getPrice());
		assertEquals(LocalDateTime.parse("2020-06-15T16:00:00"), response.getBody().getFrom());
		assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), response.getBody().getTo());
		assertEquals("EUR", response.getBody().getPriceCurrency());
	}
}
