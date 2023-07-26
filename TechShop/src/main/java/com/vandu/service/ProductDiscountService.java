package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.model.ProductDiscount;

public interface ProductDiscountService {

	void deleteAll();

	<S extends ProductDiscount> List<S> findAll(Example<S> example, Sort sort);

	<S extends ProductDiscount> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends ProductDiscount> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	ProductDiscount getReferenceById(Long id);

	void delete(ProductDiscount entity);

	ProductDiscount getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends ProductDiscount, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	ProductDiscount getOne(Long id);

	void deleteAllInBatch();

	<S extends ProductDiscount> boolean exists(Example<S> example);

	<S extends ProductDiscount> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<ProductDiscount> entities);

	Optional<ProductDiscount> findById(Long id);

	<S extends ProductDiscount> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<ProductDiscount> entities);

	List<ProductDiscount> findAllById(Iterable<Long> ids);

	List<ProductDiscount> findAll();

	<S extends ProductDiscount> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends ProductDiscount> S saveAndFlush(S entity);

	Page<ProductDiscount> findAll(Pageable pageable);

	void flush();

	List<ProductDiscount> findAll(Sort sort);

	<S extends ProductDiscount> Optional<S> findOne(Example<S> example);

	<S extends ProductDiscount> List<S> saveAll(Iterable<S> entities);

	<S extends ProductDiscount> S save(S entity);

	void deleteByProductAndDiscount(Long pid, Long did);

	ProductDiscount findByProductAndDiscount(Long pid, Long did);

}
