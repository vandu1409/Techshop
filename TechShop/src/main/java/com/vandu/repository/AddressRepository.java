package com.vandu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	@Query("select a from Address a where a.isActive = true and a.isDelete = false and a.user.userId=:uid")
	Optional<Address> getDefaultAddress(Long uid);
	
	@Query("select a from Address a where a.user.userId=:uid")
	List<Address> findAllByUser(Long uid);
	
	@Query("select a from Address a where a.user.userId=:uid and a.isDelete = :isDetele")
	List<Address> findAllByUser(Long uid,boolean isDetele);
	
	
}
