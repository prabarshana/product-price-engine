package io.x.products.calculator.service;

import io.x.products.dto.PriceCalculatorParams;
import io.x.products.dto.ProductDetailsDto;

public interface ProductPriceCalculatorService {
	ProductDetailsDto calculateFinalPriceForQty(PriceCalculatorParams priceDetails);
}
