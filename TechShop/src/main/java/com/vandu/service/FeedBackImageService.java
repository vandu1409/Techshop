package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.model.FeedBackImage;

public interface FeedBackImageService {

	void deleteAll();

	<S extends FeedBackImage> List<S> findAll(Example<S> example, Sort sort);

	<S extends FeedBackImage> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends FeedBackImage> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	FeedBackImage getReferenceById(Long id);

	void delete(FeedBackImage entity);

	FeedBackImage getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends FeedBackImage, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	FeedBackImage getOne(Long id);

	void deleteAllInBatch();

	<S extends FeedBackImage> boolean exists(Example<S> example);

	<S extends FeedBackImage> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<FeedBackImage> entities);

	Optional<FeedBackImage> findById(Long id);

	<S extends FeedBackImage> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<FeedBackImage> entities);

	List<FeedBackImage> findAllById(Iterable<Long> ids);

	List<FeedBackImage> findAll();

	<S extends FeedBackImage> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends FeedBackImage> S saveAndFlush(S entity);

	Page<FeedBackImage> findAll(Pageable pageable);

	void flush();

	List<FeedBackImage> findAll(Sort sort);

	<S extends FeedBackImage> Optional<S> findOne(Example<S> example);

	<S extends FeedBackImage> List<S> saveAll(Iterable<S> entities);

	<S extends FeedBackImage> S save(S entity);

}
