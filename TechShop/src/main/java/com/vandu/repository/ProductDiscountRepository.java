package com.vandu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vandu.model.ProductDiscount;

@Repository
public interface ProductDiscountRepository extends JpaRepository<ProductDiscount, Long>{

	@Modifying
	@Transactional
	@Query("delete from ProductDiscount p where p.product.productId = :pid and p.discount.id = :did")
	void deleteByProductAndDiscount(@Param("pid")Long pid,@Param("did")Long did);
	
	@Query("select p from ProductDiscount p where p.product.productId = :pid and p.discount.id = :did")
	ProductDiscount findByProductAndDiscount(@Param("pid")Long pid,@Param("did")Long did);
}
