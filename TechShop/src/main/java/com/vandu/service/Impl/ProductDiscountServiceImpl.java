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

import com.vandu.model.ProductDiscount;
import com.vandu.repository.ProductDetailsRepository;
import com.vandu.repository.ProductDiscountRepository;
import com.vandu.service.ProductDiscountService;

@Service
public class ProductDiscountServiceImpl implements ProductDiscountService{
	
	@Override
	public ProductDiscount findByProductAndDiscount(Long pid, Long did) {
		return productDiscountRepository.findByProductAndDiscount(pid, did);
	}

	@Autowired
	private ProductDiscountRepository productDiscountRepository;

	@Override
	public <S extends ProductDiscount> S save(S entity) {
		return productDiscountRepository.save(entity);
	}

	@Override
	public <S extends ProductDiscount> List<S> saveAll(Iterable<S> entities) {
		return productDiscountRepository.saveAll(entities);
	}

	@Override
	public <S extends ProductDiscount> Optional<S> findOne(Example<S> example) {
		return productDiscountRepository.findOne(example);
	}

	@Override
	public void deleteByProductAndDiscount(Long pid, Long did) {
		productDiscountRepository.deleteByProductAndDiscount(pid, did);
	}

	@Override
	public List<ProductDiscount> findAll(Sort sort) {
		return productDiscountRepository.findAll(sort);
	}

	@Override
	public void flush() {
		productDiscountRepository.flush();
	}

	@Override
	public Page<ProductDiscount> findAll(Pageable pageable) {
		return productDiscountRepository.findAll(pageable);
	}

	@Override
	public <S extends ProductDiscount> S saveAndFlush(S entity) {
		return productDiscountRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends ProductDiscount> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productDiscountRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<ProductDiscount> findAll() {
		return productDiscountRepository.findAll();
	}

	@Override
	public List<ProductDiscount> findAllById(Iterable<Long> ids) {
		return productDiscountRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<ProductDiscount> entities) {
		productDiscountRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends ProductDiscount> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productDiscountRepository.findAll(example, pageable);
	}

	@Override
	public Optional<ProductDiscount> findById(Long id) {
		return productDiscountRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<ProductDiscount> entities) {
		productDiscountRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return productDiscountRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productDiscountRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends ProductDiscount> long count(Example<S> example) {
		return productDiscountRepository.count(example);
	}

	@Override
	public <S extends ProductDiscount> boolean exists(Example<S> example) {
		return productDiscountRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		productDiscountRepository.deleteAllInBatch();
	}

	@Override
	public ProductDiscount getOne(Long id) {
		return productDiscountRepository.getOne(id);
	}

	@Override
	public <S extends ProductDiscount, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productDiscountRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return productDiscountRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		productDiscountRepository.deleteById(id);
	}

	@Override
	public ProductDiscount getById(Long id) {
		return productDiscountRepository.getById(id);
	}

	@Override
	public void delete(ProductDiscount entity) {
		productDiscountRepository.delete(entity);
	}

	@Override
	public ProductDiscount getReferenceById(Long id) {
		return productDiscountRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productDiscountRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends ProductDiscount> entities) {
		productDiscountRepository.deleteAll(entities);
	}

	@Override
	public <S extends ProductDiscount> List<S> findAll(Example<S> example) {
		return productDiscountRepository.findAll(example);
	}

	@Override
	public <S extends ProductDiscount> List<S> findAll(Example<S> example, Sort sort) {
		return productDiscountRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		productDiscountRepository.deleteAll();
	}
	
	

}
