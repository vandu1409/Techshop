package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.model.ProductVersion;

public interface ProductVersionService {

	<S extends ProductVersion> List<S> saveAll(Iterable<S> entities);

	<S extends ProductVersion> S save(S entity);

	ProductVersion getReferenceById(Long id);

	ProductVersion getById(Long id);

	ProductVersion getOne(Long id);

	<S extends ProductVersion> List<S> saveAllAndFlush(Iterable<S> entities);

	void flush();

	<S extends ProductVersion> Optional<S> findOne(Example<S> example);

	Optional<ProductVersion> findById(Long id);

	<S extends ProductVersion, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	List<ProductVersion> findAllById(Iterable<Long> ids);

	List<ProductVersion> findAll(Sort sort);

	Page<ProductVersion> findAll(Pageable pageable);

	<S extends ProductVersion> List<S> findAll(Example<S> example, Sort sort);

	<S extends ProductVersion> List<S> findAll(Example<S> example);

	<S extends ProductVersion> Page<S> findAll(Example<S> example, Pageable pageable);

	List<ProductVersion> findAll();

	boolean existsById(Long id);

	<S extends ProductVersion> boolean exists(Example<S> example);

	void deleteById(Long id);

	void deleteAllInBatch();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllInBatch(Iterable<ProductVersion> entities);

	void deleteInBatch(Iterable<ProductVersion> entities);

	<S extends ProductVersion> S saveAndFlush(S entity);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends ProductVersion> entities);

	void deleteAll();

	void delete(ProductVersion entity);

	<S extends ProductVersion> long count(Example<S> example);

	long count();

	Page<ProductVersion> findByProduct(Long productId, Pageable pageable);

	Page<ProductVersion> findByProduct(Long productId, String keyword, Pageable pageable);

}
