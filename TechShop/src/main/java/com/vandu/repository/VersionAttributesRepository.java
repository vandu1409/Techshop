package com.vandu.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.VersionAttributes;

@Repository
public interface VersionAttributesRepository extends JpaRepository<VersionAttributes, Long>{
	
	@Query("select v from VersionAttributes v where v.productVersion.productVersionId = :id")
	Page<VersionAttributes> getAllByVersion(Long id,Pageable pageable);

}
