package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.model.User;
import com.vandu.model.WishList;

public interface WishListService {

	void deleteAll();

	<S extends WishList> List<S> findAll(Example<S> example, Sort sort);

	<S extends WishList> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends WishList> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	WishList getReferenceById(Long id);

	void delete(WishList entity);

	WishList getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends WishList, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	WishList getOne(Long id);

	void deleteAllInBatch();

	<S extends WishList> boolean exists(Example<S> example);

	<S extends WishList> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<WishList> entities);

	Optional<WishList> findById(Long id);

	<S extends WishList> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<WishList> entities);

	List<WishList> findAllById(Iterable<Long> ids);

	List<WishList> findAll();

	<S extends WishList> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends WishList> S saveAndFlush(S entity);

	Page<WishList> findAll(Pageable pageable);

	void flush();

	List<WishList> findAll(Sort sort);

	<S extends WishList> Optional<S> findOne(Example<S> example);

	<S extends WishList> List<S> saveAll(Iterable<S> entities);

	<S extends WishList> S save(S entity);

	Optional<WishList> findByUserAndProduct(Long uid, Long pid);

	List<WishList> findByUser(Long uid);

	void deleteWishlist(Long id, User user);

}
