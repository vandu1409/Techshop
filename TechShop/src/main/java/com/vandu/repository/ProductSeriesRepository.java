package com.vandu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.ProductSeries;

@Repository
public interface ProductSeriesRepository extends JpaRepository<ProductSeries, Long>{

	@Query("select p from ProductSeries p where p.brand.brandId = :brandId ")
	Page<ProductSeries> findAllByBrand(Long brandId,Pageable pageable);
	
	@Query("select p from ProductSeries p where p.brand.brandId = :brandId ")
	List<ProductSeries> findAllByBrand(Long brandId);
	
	@Query("select p from ProductSeries p where p.brand.brandCode = :brandCode ")
	List<ProductSeries> findAllByBrandCode(String brandCode);
	
	@Query("select p from ProductSeries p where p.brand.brandId = :brandId and p.name like %:name% ")
	Page<ProductSeries> findAllByBrand(Long brandId,String name,Pageable pageable);
	
	Optional<ProductSeries> findBySeriesCode(String seriesCode);
}