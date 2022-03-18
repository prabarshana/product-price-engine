package io.x.products.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUOMCompsitId  implements Serializable {

	private static final long serialVersionUID = -7063759676458773473L;

	@Column(name = "base_uom")
	private Integer baseId;
	
	@Column(name = "to_uom")
	private Integer toId;
	
	@Column(name = "product_id")
	private Integer productId;
}
