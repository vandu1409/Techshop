package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.dto.DescriptionDto;
import com.vandu.model.Description;
import com.vandu.model.Product;

public interface DescriptionService {

	void deleteAll();

	<S extends Description> List<S> findAll(Example<S> example, Sort sort);

	<S extends Description> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Description> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Description getReferenceById(Long id);

	void delete(Description entity);

	Description getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends Description, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Description getOne(Long id);

	void deleteAllInBatch();

	<S extends Description> boolean exists(Example<S> example);

	<S extends Description> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<Description> entities);

	Optional<Description> findById(Long id);

	<S extends Description> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Description> entities);

	List<Description> findAllById(Iterable<Long> ids);

	List<Description> findAll();

	<S extends Description> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Description> S saveAndFlush(S entity);

	Page<Description> findAll(Pageable pageable);

	void flush();

	List<Description> findAll(Sort sort);

	<S extends Description> Optional<S> findOne(Example<S> example);

	<S extends Description> List<S> saveAll(Iterable<S> entities);

	<S extends Description> S save(S entity);

	Description saveOrUpdate(DescriptionDto descriptionDto, Product product);



}
