package com.vandu.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vandu.model.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {

	@Query("select v from Voucher v where v.endDate >= :today AND v.startDate <= :today")
	Page<Voucher> findValidVouchers(LocalDate today,Pageable pageable );
	
	@Query("select v from Voucher v where v.endDate < :today")
	Page<Voucher> findExpiredVouchers(LocalDate today,Pageable pageable );
	
	@Query("select v from Voucher v where v.endDate >= :today AND v.startDate <= :today")
	List<Voucher> findValidVouchers(LocalDate today );
}

