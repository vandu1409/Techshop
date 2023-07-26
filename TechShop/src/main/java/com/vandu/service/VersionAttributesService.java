package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.vandu.model.VersionAttributes;


public interface VersionAttributesService {

	void deleteAll();

	<S extends VersionAttributes> List<S> findAll(Example<S> example, Sort sort);

	<S extends VersionAttributes> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends VersionAttributes> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	VersionAttributes getReferenceById(Long id);

	void delete(VersionAttributes entity);

	VersionAttributes getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends VersionAttributes, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	VersionAttributes getOne(Long id);

	void deleteAllInBatch();

	<S extends VersionAttributes> boolean exists(Example<S> example);

	<S extends VersionAttributes> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<VersionAttributes> entities);

	Optional<VersionAttributes> findById(Long id);

	<S extends VersionAttributes> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<VersionAttributes> entities);

	List<VersionAttributes> findAllById(Iterable<Long> ids);

	List<VersionAttributes> findAll();

	<S extends VersionAttributes> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends VersionAttributes> S saveAndFlush(S entity);

	Page<VersionAttributes> findAll(Pageable pageable);

	void flush();

	List<VersionAttributes> findAll(Sort sort);

	<S extends VersionAttributes> Optional<S> findOne(Example<S> example);

	<S extends VersionAttributes> List<S> saveAll(Iterable<S> entities);

	<S extends VersionAttributes> S save(S entity);

	Page<VersionAttributes> getAllByVersion(Long id, Pageable pageable);

}
