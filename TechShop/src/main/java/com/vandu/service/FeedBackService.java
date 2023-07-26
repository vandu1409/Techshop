package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.model.FeedBack;

public interface FeedBackService {

	void deleteAll();

	<S extends FeedBack> List<S> findAll(Example<S> example, Sort sort);

	<S extends FeedBack> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends FeedBack> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	FeedBack getReferenceById(Long id);

	void delete(FeedBack entity);

	FeedBack getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends FeedBack, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	FeedBack getOne(Long id);

	void deleteAllInBatch();

	<S extends FeedBack> boolean exists(Example<S> example);

	<S extends FeedBack> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<FeedBack> entities);

	Optional<FeedBack> findById(Long id);

	<S extends FeedBack> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<FeedBack> entities);

	List<FeedBack> findAllById(Iterable<Long> ids);

	List<FeedBack> findAll();

	<S extends FeedBack> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends FeedBack> S saveAndFlush(S entity);

	Page<FeedBack> findAll(Pageable pageable);

	void flush();

	List<FeedBack> findAll(Sort sort);

	<S extends FeedBack> Optional<S> findOne(Example<S> example);

	<S extends FeedBack> List<S> saveAll(Iterable<S> entities);

	<S extends FeedBack> S save(S entity);

	Page<FeedBack> findAllByProduct(String productCode, Pageable pageable);

}
