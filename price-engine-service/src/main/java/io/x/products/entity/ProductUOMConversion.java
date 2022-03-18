package io.x.products.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUOMConversion {
	
	@EmbeddedId
	private ProductUOMCompsitId id;
	
	@ManyToOne
	@JoinColumn(name = "base_uom")
	@MapsId("baseId")
	private UOM baseUom;
	
	@ManyToOne
	@JoinColumn(name = "to_uom")
	@MapsId("toId")
	private UOM toUom;
	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	@MapsId("product")
	private Product product;
	
	@Column
	private Double conversionFactor;

}
