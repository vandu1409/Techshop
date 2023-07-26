package com.vandu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vandu.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	@Query("select c from Cart c where c.user.userId = :uid and c.productDetails.productDetailsId = :pid")
	Optional<Cart> findByUserAndProductDetails(Long uid,Long pid);
	
	@Query("select c from Cart c where c.user.userId = :uid")
	List<Cart> getAllCart(Long uid);
	
	@Query("select c from Cart c where c.user.userId = :uid and c.selectedItems = :selectedItems")
	List<Cart> getSelectedCarts(Long uid,Boolean selectedItems);
	

	@Modifying
	@Query("delete from Cart c where c.user.userId = :uid and c.selectedItems = :selectedItems")
	void deleteCartAll(@Param("uid") Long uid,Boolean selectedItems);
}
