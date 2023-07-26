package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.model.Cart;
import com.vandu.model.Order;
import com.vandu.model.OrderItem;

public interface OrderItemService {

	void deleteAll();

	<S extends OrderItem> List<S> findAll(Example<S> example, Sort sort);

	<S extends OrderItem> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends OrderItem> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	OrderItem getReferenceById(Long id);

	void delete(OrderItem entity);

	OrderItem getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends OrderItem, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	OrderItem getOne(Long id);

	void deleteAllInBatch();

	<S extends OrderItem> boolean exists(Example<S> example);

	<S extends OrderItem> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<OrderItem> entities);

	Optional<OrderItem> findById(Long id);

	<S extends OrderItem> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<OrderItem> entities);

	List<OrderItem> findAllById(Iterable<Long> ids);

	List<OrderItem> findAll();

	<S extends OrderItem> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends OrderItem> S saveAndFlush(S entity);

	Page<OrderItem> findAll(Pageable pageable);

	void flush();

	List<OrderItem> findAll(Sort sort);

	<S extends OrderItem> Optional<S> findOne(Example<S> example);

	<S extends OrderItem> List<S> saveAll(Iterable<S> entities);

	<S extends OrderItem> S save(S entity);

	void addProductsToOrder(List<Cart> list, Order order);

}
