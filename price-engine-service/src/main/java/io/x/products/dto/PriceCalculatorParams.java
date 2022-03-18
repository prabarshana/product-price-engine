package io.x.products.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceCalculatorParams {

	private Integer productId;
	private String productName;
	private Integer requestedUomId;
	private String uomCode;
	private Double basePrice;
	private Double conversionFactor;
	private Integer qty;
	private List<PricingRuleDto> rules;
	private Boolean groupingAllowed;
	
}
