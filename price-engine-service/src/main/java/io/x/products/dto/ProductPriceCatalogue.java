package io.x.products.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceCatalogue {
	
	private Integer productId;
	private String productName;
	
	private Integer uomId;
	private String uomCode;
	
	private Double unitPrice;

}
