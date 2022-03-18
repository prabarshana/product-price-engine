package io.x.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.x.products.dto.ProductDetailsDto;
import io.x.products.dto.ProductPriceCatalogue;
import io.x.products.service.ProductService;

@RestController
@RequestMapping("/api/v1/prices")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	/*
	 * 
	 * TODO Expose REST APIS to save data and secure it with ROLE_ADMIN
	 * 
	 * */

	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/product")
	public ResponseEntity<List<ProductPriceCatalogue>> getAllProductPricings() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<ProductPriceCatalogue>> getProductPricingByProductId(@PathVariable("productId") Integer productId) {
		return ResponseEntity.ok(productService.getProductPricingByProductId(productId));
	}

	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/product/{productId}/unit/{uomId}/qty/{qty}")
	public ResponseEntity<ProductDetailsDto> getPriceByProductandQty(@PathVariable("productId") Integer productId,
			@PathVariable("uomId") Integer uomId, @PathVariable("qty") Integer qty) {
		return ResponseEntity.ok(productService.getPriceByProductAndQty(productId,uomId, qty));
	}

	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/list/product/{productId}/unit/{uomId}/qty/{qty}")
	public ResponseEntity<List<ProductDetailsDto>> getPriceListByProductAndQty(
			@PathVariable("productId") Integer productId, @PathVariable("uomId") Integer uomId,
			@PathVariable("qty") Integer qty) {
		return ResponseEntity.ok(productService.getPriceListByProductAndQty(productId,uomId, qty));
	}

}
