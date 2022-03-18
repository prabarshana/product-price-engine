package io.x.products.service;

import java.util.List;

import io.x.products.dto.ProductDetailsDto;
import io.x.products.dto.ProductPriceCatalogue;
import io.x.products.entity.ProductUOMConversion;
import io.x.products.entity.PriceCatalogue;
import io.x.products.entity.PricingRule;
import io.x.products.entity.Product;
import io.x.products.entity.UOM;

public interface ProductService {

	Product addProduct(Product product);
	
	UOM addUom(UOM uom);
	
	ProductUOMConversion addCartonConfig(ProductUOMConversion uomConversion);
	
	PriceCatalogue addPriceCatalogue(PriceCatalogue priceCatalogue);
	
	List<ProductPriceCatalogue> getAllProducts();
	
	List<ProductPriceCatalogue> getProductPricingByProductId(Integer productId);
	
	ProductDetailsDto getPriceByProductAndQty(Integer productId,Integer uomId, Integer qty);
	
	List<ProductDetailsDto> getPriceListByProductAndQty(Integer productId,Integer uomId, Integer qty);
	
	PricingRule addPricingRules(PricingRule rule);
}
