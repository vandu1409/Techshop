package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.model.Color;

public interface ColorService {

	<S extends Color> List<S> saveAll(Iterable<S> entities);

	<S extends Color> S save(S entity);

	Color getReferenceById(Long id);

	Color getById(Long id);

	Color getOne(Long id);

	<S extends Color> List<S> saveAllAndFlush(Iterable<S> entities);

	void flush();

	<S extends Color> Optional<S> findOne(Example<S> example);

	Optional<Color> findById(Long id);

	<S extends Color, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	List<Color> findAllById(Iterable<Long> ids);

	List<Color> findAll(Sort sort);

	Page<Color> findAll(Pageable pageable);

	<S extends Color> List<S> findAll(Example<S> example, Sort sort);

	<S extends Color> List<S> findAll(Example<S> example);

	<S extends Color> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Color> findAll();

	boolean existsById(Long id);

	<S extends Color> boolean exists(Example<S> example);

	void deleteById(Long id);

	void deleteAllInBatch();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllInBatch(Iterable<Color> entities);

	void deleteInBatch(Iterable<Color> entities);

	<S extends Color> S saveAndFlush(S entity);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends Color> entities);

	void deleteAll();

	void delete(Color entity);

	<S extends Color> long count(Example<S> example);

	long count();

	Page<Color> findByNameContaining(String name, Pageable pageable);

}
