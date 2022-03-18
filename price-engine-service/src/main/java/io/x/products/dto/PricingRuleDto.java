package io.x.products.dto;

import io.x.products.enums.PricingRuleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PricingRuleDto {

	private Integer productId;
	private PricingRuleType ruleType;
	private Integer threshold;
	private Double percentage;
	
	
}
