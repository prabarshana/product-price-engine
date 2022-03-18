package io.x.products.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.x.products.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query(value = "select p.id as product_id, p.product_name as product_name, u.uom_code as uom_code, pc.price as price, u.id as uom_id from product p \r\n"
			+ "inner join price_catalogue pc \r\n"
			+ "on pc.product_id = p.id \r\n"
			+ "inner join uom u \r\n"
			+ "on pc.uom_id = u.id and (:productId = 0 or p.id = :productId)" , nativeQuery = true)
	List<Tuple> getProductDetailsByProductId(@Param("productId") Integer productId);
	
	
}
