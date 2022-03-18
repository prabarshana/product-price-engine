package io.x.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.x.products.entity.PricingRule;

public interface PriceRuleRepository extends JpaRepository<PricingRule, Integer>{

	List<PricingRule> findByProductId(Integer productId);
	
}
