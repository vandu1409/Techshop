package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.model.Attributes;

public interface AttributesService {

	void deleteAll();

	<S extends Attributes> List<S> findAll(Example<S> example, Sort sort);

	<S extends Attributes> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Attributes> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Attributes getReferenceById(Long id);

	void delete(Attributes entity);

	Attributes getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends Attributes, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Attributes getOne(Long id);

	void deleteAllInBatch();

	<S extends Attributes> boolean exists(Example<S> example);

	<S extends Attributes> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<Attributes> entities);

	Optional<Attributes> findById(Long id);

	<S extends Attributes> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Attributes> entities);

	List<Attributes> findAllById(Iterable<Long> ids);

	List<Attributes> findAll();

	<S extends Attributes> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Attributes> S saveAndFlush(S entity);

	Page<Attributes> findAll(Pageable pageable);

	void flush();

	List<Attributes> findAll(Sort sort);

	<S extends Attributes> Optional<S> findOne(Example<S> example);

	<S extends Attributes> List<S> saveAll(Iterable<S> entities);

	<S extends Attributes> S save(S entity);

}
