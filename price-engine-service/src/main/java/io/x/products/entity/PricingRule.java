package io.x.products.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.x.products.enums.PricingRuleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PricingRule {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pricing_rules_seq")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="product_id", referencedColumnName = "id")
	private Product product;
	
	@Column
	private PricingRuleType ruleType;
	
	@Column
	private Integer threshold;
	
	@Column
	private Double percentage;
	
}
