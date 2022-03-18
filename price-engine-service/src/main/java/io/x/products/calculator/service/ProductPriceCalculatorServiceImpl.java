package io.x.products.calculator.service;

import org.springframework.stereotype.Service;

import io.x.products.dto.PriceCalculatorParams;
import io.x.products.dto.PricingRuleDto;
import io.x.products.dto.ProductDetailsDto;
import io.x.products.enums.PricingRuleType;

@Service
public class ProductPriceCalculatorServiceImpl implements ProductPriceCalculatorService {

	@Override
	public ProductDetailsDto calculateFinalPriceForQty(PriceCalculatorParams priceDetails) {

		//TODO convert calculations to Big Decimal for better Accuracy
		//TODO extract this logic to a Jboss Drools rule base micro-service
		
		int baseUnits = priceDetails.getQty();
		int remainingSubUnits =  0;
		double conversionFactor = 1.0;

		if (priceDetails.getGroupingAllowed()) {
			conversionFactor = priceDetails.getConversionFactor();
			//Quotient = base units
			baseUnits = (int) (priceDetails.getQty() / conversionFactor);
			//remainder = sub units
			remainingSubUnits = (int) (priceDetails.getQty() % conversionFactor);
		} 

		// get the base price for the product
		double basePrice = priceDetails.getBasePrice();

		// calculate carton price based on the conversion factor
		double baseCartonPrice = basePrice * conversionFactor * baseUnits;

		// calculate base unit price
		double baseUnitPrice = basePrice * remainingSubUnits;

		// Assumption is single rule available for each type
		// get discount rule
		PricingRuleDto discountRule = priceDetails.getRules().stream()
				.filter(rule -> rule.getRuleType().equals(PricingRuleType.DISCOUNT)).iterator().next();
		int discountThreashold = discountRule.getThreshold();
		double discountPercentage = discountRule.getPercentage();

		// get labour cost rule
		PricingRuleDto costRule = priceDetails.getRules().stream()
				.filter(rule -> rule.getRuleType().equals(PricingRuleType.COST_ADD)).iterator().next();
		double unitLabour = costRule.getPercentage();
		int costThreshold = costRule.getThreshold();

		double discount = 0.0;
		double unitLabourAmount = 0.0;
		double totalCartonPrice = baseCartonPrice;
		double totalUnitPrice = baseUnitPrice;

		// calculate discount
		if (baseUnits >= discountThreashold) {
			discount = baseCartonPrice * discountPercentage;
			totalCartonPrice = baseCartonPrice - discount;
		}
		// calculate labor cost
		if (remainingSubUnits >= costThreshold) {
			unitLabourAmount = baseUnitPrice * unitLabour;
			totalUnitPrice = baseUnitPrice + unitLabourAmount;
		}

		return ProductDetailsDto.builder().productId(priceDetails.getProductId())
				.productName(priceDetails.getProductName()).uomCode(priceDetails.getUomCode())
				.totalOrderUnits(priceDetails.getQty())

				.noOfCartons(baseUnits).cartonBasePrice(baseCartonPrice).cartonDiscount(discount)
				.cartonTotalPrice(totalCartonPrice)

				.noOfUnits(remainingSubUnits).unitBasePrice(baseUnitPrice).unitLabourCost(unitLabourAmount)
				.unitTotalPrice(totalUnitPrice)

				.totalCheckoutPrice(totalCartonPrice + totalUnitPrice).build();

	}

}
