package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.dto.response.CartResponseDto;
import com.vandu.model.Cart;

public interface CartService {

	<S extends Cart> List<S> saveAll(Iterable<S> entities);

	<S extends Cart> S save(S entity);

	Cart getReferenceById(Long id);

	Cart getById(Long id);

	Cart getOne(Long id);

	<S extends Cart> List<S> saveAllAndFlush(Iterable<S> entities);

	void flush();

	<S extends Cart> Optional<S> findOne(Example<S> example);

	Optional<Cart> findById(Long id);

	<S extends Cart, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	List<Cart> findAllById(Iterable<Long> ids);

	List<Cart> findAll(Sort sort);

	Page<Cart> findAll(Pageable pageable);

	<S extends Cart> List<S> findAll(Example<S> example, Sort sort);

	<S extends Cart> List<S> findAll(Example<S> example);

	<S extends Cart> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Cart> findAll();

	boolean existsById(Long id);

	<S extends Cart> boolean exists(Example<S> example);

	void deleteById(Long id);

	void deleteAllInBatch();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllInBatch(Iterable<Cart> entities);

	void deleteInBatch(Iterable<Cart> entities);

	<S extends Cart> S saveAndFlush(S entity);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends Cart> entities);

	void deleteAll();

	void delete(Cart entity);

	<S extends Cart> long count(Example<S> example);

	long count();

	Optional<Cart> findByUserAndProductDetails(Long uid, Long pid);

	Cart addToCart(Cart cart) throws Exception;

	List<Cart> getAllCart(Long uid);

	void deleteCartAll(Long uid,Boolean selectedItems);

	List<Cart> getSelectedCarts(Long uid, Boolean selectedItems);

	CartResponseDto convertCartResponseDto(Cart item);

	void setAllSelectedItemsToFalse();



}
