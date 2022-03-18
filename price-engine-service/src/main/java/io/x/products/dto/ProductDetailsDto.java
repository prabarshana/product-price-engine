package io.x.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDto {

	private Integer productId;
	private String productName;
	private String uomCode;
	private Integer totalOrderUnits;
	
	
	private Integer noOfCartons;
	private Double cartonBasePrice;
	private Double cartonDiscount;
	private Double cartonTotalPrice;
	
	private Integer noOfUnits;
	private Double unitBasePrice;
	private Double unitLabourCost;
	private Double unitTotalPrice;
	
	private Double totalCheckoutPrice;
}
