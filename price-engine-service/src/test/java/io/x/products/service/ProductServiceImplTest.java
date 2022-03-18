package io.x.products.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import io.x.products.calculator.service.ProductPriceCalculatorServiceImpl;
import io.x.products.dto.ProductPriceCatalogue;
import io.x.products.entity.ProductUOMConversion;
import io.x.products.entity.ProductUOMCompsitId;
import io.x.products.entity.PriceCatalogue;
import io.x.products.entity.Product;
import io.x.products.entity.UOM;
import io.x.products.repository.CartonConfigRepo;
import io.x.products.repository.PriceCatalogueRepository;
import io.x.products.repository.PriceRuleRepository;
import io.x.products.repository.ProductRepository;
import io.x.products.repository.UomRepository;

@DataJpaTest
public class ProductServiceImplTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UomRepository uomRepository;

	@Autowired
	private CartonConfigRepo uomConversionRepo;

	@Autowired
	private PriceCatalogueRepository priceCatalogueRepo;

	@Autowired
	private PriceRuleRepository priceRuleRepo;

	private ProductServiceImpl service;

	private ProductPriceCalculatorServiceImpl priceService;

	@BeforeEach
	void setUp() {

		priceService = new ProductPriceCalculatorServiceImpl();
		service = new ProductServiceImpl(productRepository, uomRepository, uomConversionRepo, priceCatalogueRepo,
				priceService, priceRuleRepo);
	}

	@Test
	public void test_AddProduct() throws Exception {
		// given
		Product product = Product.builder().productCode("TestProd").productName("Test Product").build();

		// when

		Product returnProd = service.addProduct(product);
		// then
		assertThat(returnProd.getId()).isNotNull();
	}

	@Test
	public void test_AddUom() throws Exception {

		UOM uom = UOM.builder().uomCode("CT").description("TestUom").build();

		UOM returnUom = service.addUom(uom);

		assertThat(returnUom.getId()).isNotNull();
	}

	@Test
	public void test_addUOMConversion() throws Exception {

		UOM from = UOM.builder().uomCode("FR").description("TestUom1").build();

		UOM returnFrom = service.addUom(from);

		UOM to = UOM.builder().uomCode("TO").description("TestUom1").build();

		UOM returnTo = service.addUom(to);

		ProductUOMCompsitId id = ProductUOMCompsitId.builder().baseId(returnFrom.getId()).toId(returnTo.getId()).build();

		ProductUOMConversion uomConversion = ProductUOMConversion.builder().id(id).baseUom(from).toUom(to).conversionFactor(1d).build();

		ProductUOMConversion returnUomc = service.addCartonConfig(uomConversion);

		assertThat(returnUomc.getId()).isNotNull();
	}

	@Test
	public void test_addPriceCatalogue() throws Exception {

		UOM unit = UOM.builder().uomCode("UNIT").description("Single Unit").build();
		service.addUom(unit);

		Product pe = Product.builder().productName("Penguin-ears").productCode("PE").build();
		service.addProduct(pe);

		PriceCatalogue pengPrice = PriceCatalogue.builder().product(pe).uom(unit).price(8.75d).build();
		PriceCatalogue returnCatalogue = service.addPriceCatalogue(pengPrice);

		assertThat(returnCatalogue.getId()).isNotNull();
	}

	@Test
	public void test_getAllProducts() {
		// given
		UOM unit = UOM.builder().uomCode("UNIT").description("Single Unit").build();
		service.addUom(unit);
		Product pe = Product.builder().productName("Penguin-ears").productCode("PE").build();
		service.addProduct(pe);
		PriceCatalogue pengPrice = PriceCatalogue.builder().product(pe).uom(unit).price(8.75d).build();
		service.addPriceCatalogue(pengPrice);

		// when
		List<ProductPriceCatalogue> products = service.getAllProducts();

		// then
		assertThat(products).isNotEmpty();
	}

}