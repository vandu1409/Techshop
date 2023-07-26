package com.vandu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vandu.model.Order;
import com.vandu.model.Payments;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {

	@Query("select p from Payments p where p.order.orderId = :orderId")
	Optional<Payments> findByOrder(Long orderId);
}
