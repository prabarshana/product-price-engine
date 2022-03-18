package io.x.products.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UOM {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uom_seq")
	private Integer id;
	
	@Column
	private String uomCode;
	
	@Column
	private String description;
	
	@Column
	private Boolean groupingAllowed;
	
}
