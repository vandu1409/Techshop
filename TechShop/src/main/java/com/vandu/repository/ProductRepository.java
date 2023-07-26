package com.vandu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Page<Product> findByNameContaining(String name,Pageable pageable);
	
//	@Query("select p from product p where p.brand.branId= :brandId and p.productSeries.id = :productSeriesId and p.price>= :min and p.price <= :max")
//	Page<Product> filterProduct();
	
	  Page<Product> findAll(Specification<Product> spec, Pageable pageable);
	  
	  Optional<Product> findByProductCode(String productCode);
	  
	  @Query("select p from Product p where p.category.categoryCode = :categoryCode")
	  List<Product> findByCategoryCode(String categoryCode);   
	  
	  @Query("select p from Product p where p.name like %:key%")
	  Page<Product> findByKeyword(String key,Pageable pageable);
	  
	  @Query("select p from Product p where p.name like %:key%")
	  List<Product> findByKeyword(String key);
	  
	  
//	  @Query("select p from ProductDetails p where p.productVersions.productDetails ")
//	  Page<Product> getBestSellingProducts(Pageable pageable);
	  
//	    @Query(value = "SELECT TOP 5 FROM products ORDER BY NEWID() ", nativeQuery = true)
//	    List<Product> findRandomProducts(int limit);
}
