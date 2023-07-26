package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.enums.PaymentMethod;
import com.vandu.enums.PaymentStatus;
import com.vandu.model.Order;
import com.vandu.model.Payments;

public interface PaymentsService {

	void deleteAll();

	<S extends Payments> List<S> findAll(Example<S> example, Sort sort);

	<S extends Payments> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Payments> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Payments getReferenceById(Long id);

	void delete(Payments entity);

	Payments getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends Payments, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Payments getOne(Long id);

	void deleteAllInBatch();

	<S extends Payments> boolean exists(Example<S> example);

	<S extends Payments> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<Payments> entities);

	Optional<Payments> findById(Long id);

	<S extends Payments> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Payments> entities);

	List<Payments> findAllById(Iterable<Long> ids);

	List<Payments> findAll();

	<S extends Payments> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Payments> S saveAndFlush(S entity);

	Page<Payments> findAll(Pageable pageable);

	void flush();

	List<Payments> findAll(Sort sort);

	<S extends Payments> Optional<S> findOne(Example<S> example);

	<S extends Payments> List<S> saveAll(Iterable<S> entities);

	<S extends Payments> S save(S entity);

	Payments addToPayment(Order DBOrder, PaymentMethod paymentMethod, PaymentStatus paymentStatus);

	Optional<Payments> findByOrder(Long orderId);

	
}
