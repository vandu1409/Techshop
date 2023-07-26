package com.vandu.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.Discount;
import com.vandu.model.Voucher;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
	@Query("select d from Discount d where :today between d.startDate and d.endDate or d.endDate >= :today")
	Page<Discount> findValidDicounts(LocalDate today,Pageable pageable );
	
	Page<Discount> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date todayStart, Date todayEnd,Pageable pageable);
	
	@Query("select d from Discount d where d.endDate < :today")
	Page<Discount> findExpiredDiscounts(LocalDate today,Pageable pageable );
	
	@Query("select d from Discount d where d.endDate >= :today AND d.startDate <= :today")
	List<Discount> findValidDicounts(LocalDate today );
}
