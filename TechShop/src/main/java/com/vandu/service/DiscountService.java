package com.vandu.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.dto.DiscountDto;
import com.vandu.model.Discount;

public interface DiscountService {

	<S extends Discount> List<S> saveAll(Iterable<S> entities);

	<S extends Discount> S save(S entity);

	Discount getReferenceById(Long id);

	Discount getById(Long id);

	Discount getOne(Long id);

	<S extends Discount> List<S> saveAllAndFlush(Iterable<S> entities);

	void flush();

	<S extends Discount> Optional<S> findOne(Example<S> example);

	Optional<Discount> findById(Long id);

	<S extends Discount, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	List<Discount> findAllById(Iterable<Long> ids);

	List<Discount> findAll(Sort sort);

	Page<Discount> findAll(Pageable pageable);

	<S extends Discount> List<S> findAll(Example<S> example, Sort sort);

	<S extends Discount> List<S> findAll(Example<S> example);

	<S extends Discount> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Discount> findAll();

	boolean existsById(Long id);

	<S extends Discount> boolean exists(Example<S> example);

	void deleteById(Long id);

	void deleteAllInBatch();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllInBatch(Iterable<Discount> entities);

	void deleteInBatch(Iterable<Discount> entities);

	<S extends Discount> S saveAndFlush(S entity);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends Discount> entities);

	void deleteAll();

	void delete(Discount entity);

	<S extends Discount> long count(Example<S> example);

	long count();

	DiscountDto findDiscountDtoById(Long id);



	Page<Discount> findByStatus(Date today, Pageable pageable, int status);


	Page<Discount> findValidDiscounts(Pageable pageable);

	List<Discount> findValidDicounts();

	Page<Discount> findExpiredDiscounts(Pageable pageable);

}
