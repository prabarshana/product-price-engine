package io.x.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.x.products.entity.UOM;

@Repository
public interface UomRepository extends JpaRepository<UOM, Integer>{
	
	UOM findUomById(Integer id);

}
