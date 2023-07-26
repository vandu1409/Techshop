package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.model.CategoryAttributes;

public interface CategoryAttributesService {

	void deleteAll();

	<S extends CategoryAttributes> List<S> findAll(Example<S> example, Sort sort);

	<S extends CategoryAttributes> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends CategoryAttributes> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	CategoryAttributes getReferenceById(Long id);

	void delete(CategoryAttributes entity);

	CategoryAttributes getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends CategoryAttributes, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	CategoryAttributes getOne(Long id);

	void deleteAllInBatch();

	<S extends CategoryAttributes> boolean exists(Example<S> example);

	<S extends CategoryAttributes> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<CategoryAttributes> entities);

	Optional<CategoryAttributes> findById(Long id);

	<S extends CategoryAttributes> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<CategoryAttributes> entities);

	List<CategoryAttributes> findAllById(Iterable<Long> ids);

	List<CategoryAttributes> findAll();

	<S extends CategoryAttributes> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends CategoryAttributes> S saveAndFlush(S entity);

	Page<CategoryAttributes> findAll(Pageable pageable);

	void flush();

	List<CategoryAttributes> findAll(Sort sort);

	<S extends CategoryAttributes> Optional<S> findOne(Example<S> example);

	<S extends CategoryAttributes> List<S> saveAll(Iterable<S> entities);

	<S extends CategoryAttributes> S save(S entity);

	Page<CategoryAttributes> findAllByCategory(Long id, Pageable pageable);

	CategoryAttributes findByCategoryAndAttributes(Long cid, Long aid);

	Page<CategoryAttributes> findAllAtrributesByCategory(Long id, Pageable pageable);

//	Page<CategoryAttributes> findAllByProduct(Long productId, Pageable pageable);

}
