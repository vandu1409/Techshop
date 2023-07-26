package com.vandu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long>{

	Page<Brand> findByNameContaining(String name,Pageable pageable);
	
	Optional<Brand> findByBrandCode(String brandCode);
	
//	@Query("select b from Brand b where b.productSeries.product.category.categoryCode = :categoryCode")
//	List<Brand> getAllBrandByCategory(String categoryCode);
	
	@Query("select b from Brand b where b.category.id = :id")
	Page<Brand> findAllByCategory(Long id,Pageable pageable);
	
	@Query("select b from Brand b where b.category.id = :id and b.name like %:keyword%")
	Page<Brand> findAllByCategoryAndKeyword(Long id,String keyword,Pageable pageable);
	
	@Query("select b from Brand b where b.category.id = :id")
	List<Brand> findAllByCategory(Long id);
	
	@Query("select b from Brand b where b.category.categoryCode = :categoryCode")
	List<Brand> findAllByCategoryCode(String categoryCode);
}
