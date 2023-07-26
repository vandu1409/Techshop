package com.vandu.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vandu.model.ProductAttributes;

@Repository
public interface ProductAttributesReposiory extends JpaRepository<ProductAttributes, Long>{
	@Query("select p from ProductAttributes p where p.product.productId = :productId")
	Page<ProductAttributes> findAllByProduct(@Param("productId")Long productId,Pageable pageable);
	
	@Query("select p from ProductAttributes p where p.product.productId = :productId and p.attributes.id = :attributesId")
	ProductAttributes findByProductAndAttributes(Long productId,Long attributesId);
	
}
