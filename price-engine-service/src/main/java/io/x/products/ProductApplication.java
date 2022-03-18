package io.x.products;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import io.x.products.entity.ProductUOMConversion;
import io.x.products.entity.ProductUOMCompsitId;
import io.x.products.entity.PriceCatalogue;
import io.x.products.entity.PricingRule;
import io.x.products.entity.Product;
import io.x.products.entity.UOM;
import io.x.products.enums.PricingRuleType;
import io.x.products.service.ProductService;

@EnableEurekaClient
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProductApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(ProductService service) {
//
//		return x -> {
//
//			UOM unit = UOM.builder().uomCode("UNIT").description("Single Unit").groupingAllowed(Boolean.TRUE).build();
//			service.addUom(unit);
//
//			UOM carton = UOM.builder().uomCode("CARTON").description("Carton Unit").groupingAllowed(Boolean.FALSE).build();
//			service.addUom(carton);
//
//			Product pe = Product.builder().productName("Penguin-ears").productCode("PE").build();
//			service.addProduct(pe);
//			
//			ProductUOMConversion penguinCarton = ProductUOMConversion.builder().baseUom(unit).toUom(carton).conversionFactor(20d)
//					.product(pe).id(ProductUOMCompsitId.builder().baseId(unit.getId()).toId(carton.getId())
//							.productId(pe.getId()).build())
//					.build();
//			
//			service.addCartonConfig(penguinCarton);
//			
//			
//			ProductUOMConversion penguinUnit= ProductUOMConversion.builder().baseUom(carton).toUom(unit).conversionFactor(0.05d)
//					.product(pe).id(ProductUOMCompsitId.builder().baseId(carton.getId()).toId(unit.getId())
//							.productId(pe.getId()).build())
//					.build();
//			
//			service.addCartonConfig(penguinUnit);
//			
//			
//			PriceCatalogue pengUnitPrice = PriceCatalogue.builder().product(pe).price(8.75d).uom(unit).build();
//			service.addPriceCatalogue(pengUnitPrice);
//			
//			PriceCatalogue pengCartonPrice = PriceCatalogue.builder().product(pe).price(175d).uom(carton).build();
//			service.addPriceCatalogue(pengCartonPrice);
//			
//			PricingRule pgRule1 = PricingRule.builder()
//					.product(pe)
//					.ruleType(PricingRuleType.DISCOUNT)
//					.threshold(3)
//					.percentage(0.1).build();
//			
//			PricingRule pgRule2 = PricingRule.builder()
//					.product(pe)
//					.ruleType(PricingRuleType.COST_ADD)
//					.threshold(0)
//					.percentage(0.3).build();
//			
//			service.addPricingRules(pgRule1);
//			service.addPricingRules(pgRule2);
//			
//			//----
//			
//			Product hs = Product.builder().productName("Horseshoe").productCode("HS").build();
//			service.addProduct(hs);
//			
//			ProductUOMConversion hsCarton = ProductUOMConversion.builder().baseUom(unit).toUom(carton).conversionFactor(5d)
//					.product(hs).id(ProductUOMCompsitId.builder().baseId(unit.getId()).toId(carton.getId())
//							.productId(hs.getId()).build())
//					.build();
//			
//			service.addCartonConfig(hsCarton);
//			
//			ProductUOMConversion hsUnit = ProductUOMConversion.builder().baseUom(carton).toUom(unit).conversionFactor(0.2d)
//					.product(hs).id(ProductUOMCompsitId.builder().baseId(carton.getId()).toId(unit.getId())
//							.productId(hs.getId()).build())
//					.build();
//			
//			service.addCartonConfig(hsUnit);
//			
//			
//			PriceCatalogue hsUnitPrice = PriceCatalogue.builder().product(hs).price(165d).uom(unit).build();
//			
//			PriceCatalogue hsCartonPrice = PriceCatalogue.builder().product(hs).price(825d).uom(carton).build();
//			
//			service.addPriceCatalogue(hsUnitPrice);
//			service.addPriceCatalogue(hsCartonPrice);
//		
//			
//			PricingRule hsRule1 = PricingRule.builder()
//					.product(hs)
//					.ruleType(PricingRuleType.DISCOUNT)
//					.threshold(3)
//					.percentage(0.1).build();
//			
//			PricingRule hsRule2 = PricingRule.builder()
//					.product(hs)
//					.ruleType(PricingRuleType.COST_ADD)
//					.threshold(0)
//					.percentage(0.3).build();
//					
//			
//			service.addPricingRules(hsRule1);
//			service.addPricingRules(hsRule2);
//		};
//
//	}
}
