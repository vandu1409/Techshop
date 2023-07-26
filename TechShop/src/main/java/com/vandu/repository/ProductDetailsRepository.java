package com.vandu.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.Color;
import com.vandu.model.ProductDetails;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {
	
	@Query("select p from ProductDetails p where p.productVersion.productVersionId =:id")
	Page<ProductDetails> findByVersion(Long id,Pageable pageable);
	
	@Query("select p from ProductDetails p where p.productVersion.productVersionId =:id and p.color.name like %:name%")
	Page<ProductDetails> findByVersion(Long id,String name,Pageable pageable);
	
	@Query("select p from ProductDetails p where p.productVersion.productVersionId =:productVersionId and p.color.id = :colorId")
	ProductDetails findByVersionAndColor(Long productVersionId,Long colorId);
	
	@Query("select p.color from ProductDetails p where p.productVersion.productVersionId =:id ")
	List<Color> findColorByVersion(Long id);
	
	@Query("select count(p) from ProductDetails p where p.quantity < 10")
	Long countLowQuantityProducts(); // đếm số sản phẩm sắp hết hàng
	
//	@Query("select p from ProductDetails p where ")
}
