package io.x.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.x.products.entity.PriceCatalogue;

@Repository
public interface PriceCatalogueRepository extends JpaRepository<PriceCatalogue, Integer> {

	PriceCatalogue findByProductIdAndUomId(Integer productId, Integer uomId);
}
