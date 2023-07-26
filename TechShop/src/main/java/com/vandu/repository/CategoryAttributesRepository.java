package com.vandu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vandu.model.CategoryAttributes;

@Repository
public interface CategoryAttributesRepository extends JpaRepository<CategoryAttributes, Long> {
	
//	Page<ProductAttributes> findByNameContaining(S)
	
//	@Query("select p from ProductAttributes p where p.product.productId = :productId")
//	Page<CategoryAttributes> findAllByProduct(@Param("productId")Long productId,Pageable pageable);
	
	@Query("select c from CategoryAttributes c where c.category.id = :id")
	Page<CategoryAttributes> findAllByCategory(Long id,Pageable pageable);
	
	@Query("select c from CategoryAttributes c where c.category.id = :cid and c.attributes.id = :aid")
	CategoryAttributes findByCategoryAndAttributes(Long cid,Long aid);
	

	@Query("select c.attributes from CategoryAttributes c where c.category.id = :id")
	Page<CategoryAttributes> findAllAtrributesByCategory(Long id,Pageable pageable);
	
}
