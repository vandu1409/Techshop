package com.vandu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long>{

	@Query("select w from WishList w where w.user.userId = :uid and w.product.productId = :pid")
	Optional<WishList> findByUserAndProduct(Long uid,Long pid);
	
	@Query("select w from WishList w where w.user.userId = :uid")
	List<WishList> findByUser(Long uid);
}
