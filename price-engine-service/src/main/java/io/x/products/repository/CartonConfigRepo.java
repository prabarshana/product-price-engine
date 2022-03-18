package io.x.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.x.products.entity.ProductUOMConversion;
import io.x.products.entity.ProductUOMCompsitId;

@Repository
public interface CartonConfigRepo extends JpaRepository<ProductUOMConversion, ProductUOMCompsitId> {

	ProductUOMConversion findByProductIdAndBaseUomId(Integer productId, Integer baseUomId);

}
