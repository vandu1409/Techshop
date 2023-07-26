package com.vandu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.Color;
import com.vandu.model.ProductVersion;
@Repository
public interface ProductVersionRepository extends JpaRepository<ProductVersion, Long>{

	@Query("select p from ProductVersion p where p.product.productId = :productId")
	Page<ProductVersion> findByProduct(Long productId,Pageable pageable);
	
	@Query("select p from ProductVersion p where p.product.productId = :productId and p.versionName like %:keyword%")
	Page<ProductVersion> findByProduct(Long productId,String keyword,Pageable pageable);
	
	@Query("select p from ProductVersion p")
	Page<ProductVersion> findByProduct(Pageable pageable);
	
	
}
