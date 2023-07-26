package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.model.Feature;

public interface FeatureService {

	void deleteAll();

	<S extends Feature> List<S> findAll(Example<S> example, Sort sort);

	<S extends Feature> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Feature> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Feature getReferenceById(Long id);

	void delete(Feature entity);

	Feature getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends Feature, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Feature getOne(Long id);

	void deleteAllInBatch();

	<S extends Feature> boolean exists(Example<S> example);

	<S extends Feature> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<Feature> entities);

	Optional<Feature> findById(Long id);

	<S extends Feature> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Feature> entities);

	List<Feature> findAllById(Iterable<Long> ids);

	List<Feature> findAll();

	<S extends Feature> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Feature> S saveAndFlush(S entity);

	Page<Feature> findAll(Pageable pageable);

	void flush();

	List<Feature> findAll(Sort sort);

	<S extends Feature> Optional<S> findOne(Example<S> example);

	<S extends Feature> List<S> saveAll(Iterable<S> entities);

	<S extends Feature> S save(S entity);

}
