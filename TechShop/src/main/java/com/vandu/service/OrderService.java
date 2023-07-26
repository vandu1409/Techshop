package com.vandu.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.dto.PaymentResDTO;
import com.vandu.dto.response.OrderResponseDto;
import com.vandu.enums.OrderStatus;
import com.vandu.model.Order;

import jakarta.servlet.http.HttpServletRequest;

public interface OrderService {

	void deleteAll();

	<S extends Order> List<S> findAll(Example<S> example, Sort sort);

	<S extends Order> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Order> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Order getReferenceById(Long id);

	void delete(Order entity);

	Order getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends Order, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Order getOne(Long id);

	void deleteAllInBatch();

	<S extends Order> boolean exists(Example<S> example);

	<S extends Order> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<Order> entities);

	Optional<Order> findById(Long id);

	<S extends Order> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Order> entities);

	List<Order> findAllById(Iterable<Long> ids);

	List<Order> findAll();

	<S extends Order> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Order> S saveAndFlush(S entity);

	Page<Order> findAll(Pageable pageable);

	void flush();

	List<Order> findAll(Sort sort);

	<S extends Order> Optional<S> findOne(Example<S> example);

	<S extends Order> List<S> saveAll(Iterable<S> entities);

	<S extends Order> S save(S entity);

	Page<Order> findAllOrdersWithSpecificationAndPageable(Specification<Order> specification, Pageable pageable);

	List<Order> findByStatus(int status);

	OrderResponseDto convertToOrderResponseDto(Order item);

	Page<Order> findByUser(Long uid, Pageable pageable);

	List<Order> findByMonth(int month);

	List<Order> findByStatusAndMonth(int month, OrderStatus orderStatus);

	Order cancelOrder(Order order);

	Order restoreOrder(Order order);

	List<Order> findByStatusAndMonthAndYear(int month, int year, OrderStatus orderStatus);

	PaymentResDTO payementByVnPay(Long amount, Long orderId, HttpServletRequest req) throws UnsupportedEncodingException;




	

	

}
