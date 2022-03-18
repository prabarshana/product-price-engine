package io.x.products.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.stereotype.Service;

import io.x.products.calculator.service.ProductPriceCalculatorService;
import io.x.products.dto.PriceCalculatorParams;
import io.x.products.dto.PricingRuleDto;
import io.x.products.dto.ProductDetailsDto;
import io.x.products.dto.ProductPriceCatalogue;
import io.x.products.entity.ProductUOMConversion;
import io.x.products.entity.PriceCatalogue;
import io.x.products.entity.PricingRule;
import io.x.products.entity.Product;
import io.x.products.entity.UOM;
import io.x.products.exceptions.InvalidProductException;
import io.x.products.exceptions.InvalidQtyException;
import io.x.products.exceptions.InvalidUomException;
import io.x.products.exceptions.ProductNotFoundException;
import io.x.products.exceptions.UOMNotFoundException;
import io.x.products.repository.CartonConfigRepo;
import io.x.products.repository.PriceCatalogueRepository;
import io.x.products.repository.PriceRuleRepository;
import io.x.products.repository.ProductRepository;
import io.x.products.repository.UomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	private final UomRepository uomRepository;

	private final CartonConfigRepo cartonConfigRepo;

	private final PriceCatalogueRepository priceCatalogueRepo;

	private final ProductPriceCalculatorService priceCalculatorService;

	private final PriceRuleRepository priceRuleRepository;

	@Override
	public Product addProduct(Product product) {
		log.info("Adding product {} ", product);
		return productRepository.save(product);
	}

	@Override
	public UOM addUom(UOM uom) {
		log.info("Adding uom {} ", uom);
		return uomRepository.save(uom);
	}

	@Override
	public ProductUOMConversion addCartonConfig(ProductUOMConversion uomConversion) {
		log.info("Adding uom conversion {} ", uomConversion);
		return cartonConfigRepo.save(uomConversion);
	}

	@Override
	public PriceCatalogue addPriceCatalogue(PriceCatalogue priceCatalogue) {
		log.info("Adding uom priceCatalogue {} ", priceCatalogue);
		return priceCatalogueRepo.save(priceCatalogue);
	}

	@Override
	public List<ProductPriceCatalogue> getAllProducts() {

		List<Tuple> products = productRepository.getProductDetailsByProductId(0);

		return products.stream()
				.map(t -> ProductPriceCatalogue.builder().productId(t.get("product_id", Integer.class))
						.productName(t.get("product_name", String.class)).uomCode(t.get("uom_code", String.class))
						.unitPrice(t.get("price", Double.class)).uomId(t.get("uom_id", Integer.class)).build())
				.collect(Collectors.toList());

	}

	@Override
	public List<ProductPriceCatalogue> getProductPricingByProductId(Integer productId) {

		List<Tuple> products = productRepository.getProductDetailsByProductId(productId);

		return products.stream()
				.map(t -> ProductPriceCatalogue.builder().productId(t.get("product_id", Integer.class))
						.productName(t.get("product_name", String.class)).uomCode(t.get("uom_code", String.class))
						.unitPrice(t.get("price", Double.class)).uomId(t.get("uom_id", Integer.class)).build())
				.collect(Collectors.toList());

	}

	@Override
	public ProductDetailsDto getPriceByProductAndQty(Integer productId, Integer uomId, Integer qty) {

		if (qty == null || qty.intValue() == 0) {
			throw new InvalidQtyException("Please select a valid quantity!");
		}

		if (uomId == null || uomId.intValue() == 0) {
			throw new InvalidUomException("Please select a valid unit id!");
		}
		
		if (productId == null || productId.intValue() == 0) {
			throw new InvalidProductException("Please select a valid Product Id!");
		}
		
		
		Product product = productRepository.getById(productId);
		
		if (product == null) {
			throw new ProductNotFoundException("No Product found for the given product id!");
		}

		UOM requestedUom = uomRepository.getById(uomId);
		
		if (requestedUom == null) {
			throw new UOMNotFoundException("No UOM found for the given uom id!");
		}

		PriceCatalogue productBasePrice = priceCatalogueRepo.findByProductIdAndUomId(productId, uomId);

		List<PricingRule> rules = priceRuleRepository.findByProductId(productId);

		ArrayList<PricingRuleDto> ruleList = (ArrayList<PricingRuleDto>) rules.stream()
				.map(rule -> PricingRuleDto.builder().productId(rule.getProduct().getId()).ruleType(rule.getRuleType())
						.percentage(rule.getPercentage()).threshold(rule.getThreshold()).build())
				.collect(Collectors.toList());

		ProductUOMConversion baseUnitConfig = cartonConfigRepo.findByProductIdAndBaseUomId(product.getId(),
				requestedUom.getId());

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(productId)
				.productName(product.getProductName())
				.requestedUomId(requestedUom.getId()).uomCode(requestedUom.getUomCode())
				.basePrice(productBasePrice.getPrice()).conversionFactor(baseUnitConfig.getConversionFactor())
				.rules(ruleList).qty(qty).groupingAllowed(requestedUom.getGroupingAllowed()).build();

		return priceCalculatorService.calculateFinalPriceForQty(params);

	}

	@Override
	public PricingRule addPricingRules(PricingRule rule) {
		log.info("Adding uom price Rule {} ", rule);
		return priceRuleRepository.save(rule);
	}

	@Override
	public List<ProductDetailsDto> getPriceListByProductAndQty(Integer productId, Integer uomId, Integer qty) {

		if (qty == null || qty.intValue() == 0) {
			throw new InvalidQtyException("Please select a valid quantity!");
		}

		if (uomId == null || uomId.intValue() == 0) {
			throw new InvalidUomException("Please select a valid unit id!");
		}
		
		if (productId == null || productId.intValue() == 0) {
			throw new InvalidProductException("Please select a valid Product Id!");
		}

		Product product = productRepository.getById(productId);
		
		if (product == null) {
			throw new ProductNotFoundException("No product found for the given product id!");
		}

		UOM requestedUom = uomRepository.getById(uomId);
		
		if (requestedUom == null) {
			throw new UOMNotFoundException("No UOM found for the given uom id!");
		}

		PriceCatalogue productBasePrice = priceCatalogueRepo.findByProductIdAndUomId(productId, uomId);

		List<PricingRule> rules = priceRuleRepository.findByProductId(productId);

		ArrayList<PricingRuleDto> ruleList = (ArrayList<PricingRuleDto>) rules.stream()
				.map(rule -> PricingRuleDto.builder().productId(rule.getProduct().getId()).ruleType(rule.getRuleType())
						.percentage(rule.getPercentage()).threshold(rule.getThreshold()).build())
				.collect(Collectors.toList());

		ProductUOMConversion baseUnitConfig = cartonConfigRepo.findByProductIdAndBaseUomId(product.getId(),
				requestedUom.getId());

		List<ProductDetailsDto> returnResultList = new ArrayList<ProductDetailsDto>(qty);

		for (int i = 1; i <= qty; i++) {

			PriceCalculatorParams params = PriceCalculatorParams.builder().productId(productId)
					.productName(product.getProductName())
					.requestedUomId(requestedUom.getId()).uomCode(requestedUom.getUomCode())
					.basePrice(productBasePrice.getPrice()).conversionFactor(baseUnitConfig.getConversionFactor())
					.rules(ruleList).qty(i).groupingAllowed(requestedUom.getGroupingAllowed()).build();

			returnResultList.add(priceCalculatorService.calculateFinalPriceForQty(params));

		}

		return returnResultList;
	}

}
