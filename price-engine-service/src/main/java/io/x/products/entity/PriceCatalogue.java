package io.x.products.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PriceCatalogue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogue_seq")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	
	@Column
	private Double price;
	
	@OneToOne
	@JoinColumn(name = "uom_id", referencedColumnName = "id")
	private UOM uom;
	
}
