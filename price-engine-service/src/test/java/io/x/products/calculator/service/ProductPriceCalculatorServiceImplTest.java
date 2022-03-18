package io.x.products.calculator.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.x.products.dto.PriceCalculatorParams;
import io.x.products.dto.PricingRuleDto;
import io.x.products.dto.ProductDetailsDto;
import io.x.products.enums.PricingRuleType;

public class ProductPriceCalculatorServiceImplTest {

	ProductPriceCalculatorService calculatorService = new ProductPriceCalculatorServiceImpl();
	
	@Test
	public void test_pge_TCPWhenGroupingNotAllowed() throws Exception {
		// given

		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(false).uomCode("Test Base UOM").basePrice(175d).conversionFactor(1d).qty(1).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getTotalCheckoutPrice()).isEqualTo(175);
	}
	
	@Test
	public void test_pge_TCPWhenGroupingNotAllowedforTwoCartons() throws Exception {
		// given

		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(false).uomCode("Test Base UOM").basePrice(175d).conversionFactor(1d).qty(2).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getTotalCheckoutPrice()).isEqualTo(350);
	}
	
	
	@Test
	public void test_pge_TCPWhenGroupingNotAllowedforThreeCartons() throws Exception {
		// given

		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(false).uomCode("Test Base UOM").basePrice(175d).conversionFactor(1d).qty(3).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getTotalCheckoutPrice()).isEqualTo(472.5);
	}
	
	
	@Test
	public void test_pge_CDWhenGroupingNotAllowedforThreeCartons() throws Exception {
		// given

		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(false).uomCode("Test Base UOM").basePrice(175d).conversionFactor(1d).qty(3).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getCartonDiscount()).isEqualTo(52.5);
	}
	
	
	@Test
	public void test_pge_CTPWhenGroupingNotAllowedforThreeCartons() throws Exception {
		// given

		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(false).uomCode("Test Base UOM").basePrice(175d).conversionFactor(1d).qty(3).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getCartonTotalPrice()).isEqualTo(472.5);
	}
	

	@Test
	public void test_pge_TCPWhenTotalUnitsAreLessThanACarton() throws Exception {
		// given

		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(1).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getTotalCheckoutPrice()).isEqualTo(11.375);
	}
	
	
	@Test
	public void test_pge_UBPWhenTotalUnitsAreLessThanACarton() throws Exception {
		// given

		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(1).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitBasePrice()).isEqualTo(8.75);
	}
	
	
	@Test
	public void test_pge_ULCWhenTotalUnitsAreLessThanACarton() throws Exception {
		// given

		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(1).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitLabourCost()).isEqualTo(2.625);
	}

	@Test
	public void test_pge_TCPWhenTotalUnitsAreEqualToACarton() throws Exception {
		// given
		
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(20).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getTotalCheckoutPrice()).isEqualTo(175);
	}
	
	@Test
	public void test_pge_UBPWhenTotalUnitsAreEqualToACarton() throws Exception {
		// given
		
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(20).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitBasePrice()).isEqualTo(0);
	}
	
	@Test
	public void test_pge_ULCWhenTotalUnitsAreEqualToACarton() throws Exception {
		// given
		
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(20).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitLabourCost()).isEqualTo(0);
	}
	

	@Test
	public void test_pge_TCPWhenTotalUnitsAreGreaterThanCarton() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(21).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getTotalCheckoutPrice()).isEqualTo(186.375);
	}
	
	
	@Test
	public void test_pge_UBPWhenTotalUnitsAreGreaterThanCarton() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(21).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitBasePrice()).isEqualTo(8.75);
	}
	
	@Test
	public void test_pge_ULCWhenTotalUnitsAreGreaterThanCarton() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(21).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitLabourCost()).isEqualTo(2.625);
	}

	@Test
	public void test_pge_CDWhenCartonSizeIs2() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(40).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getCartonDiscount()).isEqualTo(0);
	}
	
	
	@Test
	public void test_pge_UBPWhenCartonSizeIs2() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(40).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitBasePrice()).isEqualTo(0);
	}
	
	
	@Test
	public void test_pge_ULCWhenCartonSizeIs2() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(40).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitLabourCost()).isEqualTo(0);
	}
	
	
	@Test
	public void test_pge_TCPWhenCartonSizeIs2() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(40).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getTotalCheckoutPrice()).isEqualTo(175 * 2);
	}

	@Test
	public void test_pge_CDWhenCartonSizeIs3() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(60).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getCartonDiscount()).isEqualTo(52.5);
	}
	
	
	@Test
	public void test_pgeUBPWhenCartonSizeIs3() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(60).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitBasePrice()).isEqualTo(0);
	}
	
	
	@Test
	public void test_pge_ULCWhenCartonSizeIs3() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(60).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitLabourCost()).isEqualTo(0);
	}

	@Test
	public void test_pge_CDWhenCartonSizeIs4() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(1).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));
		
		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test PGE Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(8.75d).conversionFactor(20d).qty(80).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getCartonDiscount()).isEqualTo(70);

	}
	
//
	
	@Test
	public void test_hs_TCPWhenTotalUnitsAreLessThanACarton() throws Exception {
		// given

		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(1).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getTotalCheckoutPrice()).isEqualTo(214.5);
	}
	
	
	@Test
	public void test_hs_UBPWhenTotalUnitsAreLessThanACarton() throws Exception {
		// given

		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(1).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitBasePrice()).isEqualTo(165);
	}
	
	
	@Test
	public void test_hs_ULBWhenTotalUnitsAreLessThanACarton() throws Exception {
		// given

		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(1).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitLabourCost()).isEqualTo(49.5);
	}
	

	@Test
	public void test_hs_TCPWhenTotalUnitsAreEqualToACarton() throws Exception {
		// given
		
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(5).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getTotalCheckoutPrice()).isEqualTo(825);
	}
	
	
	@Test
	public void test_hs_UBPWhenTotalUnitsAreEqualToACarton() throws Exception {
		// given
		
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(5).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitBasePrice()).isEqualTo(0);
	}
	
	
	@Test
	public void test_hs_ULCWhenTotalUnitsAreEqualToACarton() throws Exception {
		// given
		
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(5).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitLabourCost()).isEqualTo(0);
	}

	@Test
	public void test_hs_TCPWhenTotalUnitsAreGreaterThanCarton() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(6).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getTotalCheckoutPrice()).isEqualTo(1039.5);
	}
	
	
	@Test
	public void test_hs_UBPWhenTotalUnitsAreGreaterThanCarton() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(6).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitBasePrice()).isEqualTo(165);
	}
	
	
	@Test
	public void test_hs_ULCWhenTotalUnitsAreGreaterThanCarton() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(6).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitLabourCost()).isEqualTo(49.5);
	}

	@Test
	public void test_hs_CDWhenCartonSizeIs2() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(10).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getCartonDiscount()).isEqualTo(0);
	}
	
	@Test
	public void test_hs_UBPWhenCartonSizeIs2() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(10).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitBasePrice()).isEqualTo(0);
	}
	
	@Test
	public void test_hs_ULCWhenCartonSizeIs2() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(10).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitLabourCost()).isEqualTo(0);
	}
	
	@Test
	public void test_hs_TCPWhenCartonSizeIs2() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(10).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getTotalCheckoutPrice()).isEqualTo(825 * 2);
	}

	@Test
	public void test_hs_CDWhenCartonSizeIs3() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(15).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getCartonDiscount()).isEqualTo(247.5);
	}
	
	
	@Test
	public void test_hs_UBPWhenCartonSizeIs3() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(15).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitBasePrice()).isEqualTo(0);
	}
	
	
	@Test
	public void test_hs_ULCWhenCartonSizeIs3() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(15).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getUnitLabourCost()).isEqualTo(0);
	}

	@Test
	public void test_hs_CDWhenCartonSizeIs4() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(20).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getCartonDiscount()).isEqualTo(330);

	}
	
	
	@Test
	public void test_hs_TCPWhenCartonSizeIs4() throws Exception {
		// given
		List<PricingRuleDto> rules = new ArrayList<PricingRuleDto>(List.of(
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.DISCOUNT).percentage(0.1).threshold(3)
						.build(),
				PricingRuleDto.builder().productId(2).ruleType(PricingRuleType.COST_ADD).percentage(0.3).threshold(0)
						.build()));

		PriceCalculatorParams params = PriceCalculatorParams.builder().productId(1).productName("Test HS Product")
				.requestedUomId(1).groupingAllowed(true).uomCode("Test Base UOM").basePrice(165d).conversionFactor(5d).qty(20).rules(rules).build();
		// when
		ProductDetailsDto returnPrice = calculatorService.calculateFinalPriceForQty(params);
		// then
		assertThat(returnPrice.getTotalCheckoutPrice()).isEqualTo((825 * 4) - 330);

	}

}