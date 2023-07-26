package com.vandu.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.vandu.model.Cart;
import com.vandu.model.Order;
import com.vandu.model.OrderItem;
import com.vandu.model.ProductDetails;
import com.vandu.repository.OrderItemRepository;
import com.vandu.repository.ProductDetailsRepository;
import com.vandu.service.OrderItemService;
import com.vandu.service.OrderService;
import com.vandu.service.ProductDetailsService;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductDetailsRepository productDetailsRepository;
	
	
	@Override
	public void addProductsToOrder(List<Cart> list,Order order) {

		list.stream().forEach(item -> {
			OrderItem orderItem = new OrderItem();
			orderItem.setProductDetails(item.getProductDetails());
			orderItem.setOrder(order);
			orderItem.setQuantity(item.getQuantity());
			orderItem.setTotalPrice(item.getTotalPrice());
			orderItem.setInternalPrice(item.getProductDetails().getImportPrice());
			orderItem.setFeedBack(false);
			
			ProductDetails productDetails = item.getProductDetails();
			productDetails.setQuantity(productDetails.getQuantity()-item.getQuantity());
			productDetailsRepository.save(productDetails);
			
			save(orderItem);

		});
	}
	
	

	@Override
	public <S extends OrderItem> S save(S entity) {
		return orderItemRepository.save(entity);
	}

	@Override
	public <S extends OrderItem> List<S> saveAll(Iterable<S> entities) {
		return orderItemRepository.saveAll(entities);
	}

	@Override
	public <S extends OrderItem> Optional<S> findOne(Example<S> example) {
		return orderItemRepository.findOne(example);
	}

	@Override
	public List<OrderItem> findAll(Sort sort) {
		return orderItemRepository.findAll(sort);
	}

	@Override
	public void flush() {
		orderItemRepository.flush();
	}

	@Override
	public Page<OrderItem> findAll(Pageable pageable) {
		return orderItemRepository.findAll(pageable);
	}

	@Override
	public <S extends OrderItem> S saveAndFlush(S entity) {
		return orderItemRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends OrderItem> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderItemRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<OrderItem> findAll() {
		return orderItemRepository.findAll();
	}

	@Override
	public List<OrderItem> findAllById(Iterable<Long> ids) {
		return orderItemRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<OrderItem> entities) {
		orderItemRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends OrderItem> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderItemRepository.findAll(example, pageable);
	}

	@Override
	public Optional<OrderItem> findById(Long id) {
		return orderItemRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<OrderItem> entities) {
		orderItemRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return orderItemRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		orderItemRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends OrderItem> long count(Example<S> example) {
		return orderItemRepository.count(example);
	}

	@Override
	public <S extends OrderItem> boolean exists(Example<S> example) {
		return orderItemRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		orderItemRepository.deleteAllInBatch();
	}

	@Override
	public OrderItem getOne(Long id) {
		return orderItemRepository.getOne(id);
	}

	@Override
	public <S extends OrderItem, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return orderItemRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return orderItemRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		orderItemRepository.deleteById(id);
	}

	@Override
	public OrderItem getById(Long id) {
		return orderItemRepository.getById(id);
	}

	@Override
	public void delete(OrderItem entity) {
		orderItemRepository.delete(entity);
	}

	@Override
	public OrderItem getReferenceById(Long id) {
		return orderItemRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		orderItemRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderItem> entities) {
		orderItemRepository.deleteAll(entities);
	}

	@Override
	public <S extends OrderItem> List<S> findAll(Example<S> example) {
		return orderItemRepository.findAll(example);
	}

	@Override
	public <S extends OrderItem> List<S> findAll(Example<S> example, Sort sort) {
		return orderItemRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		orderItemRepository.deleteAll();
	}
	
	
}
