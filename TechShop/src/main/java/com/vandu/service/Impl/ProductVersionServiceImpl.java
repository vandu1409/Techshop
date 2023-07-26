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

import com.vandu.model.ProductVersion;
import com.vandu.repository.ProductVersionRepository;
import com.vandu.service.ProductVersionService;

@Service
public class ProductVersionServiceImpl implements ProductVersionService {

	@Autowired
	private ProductVersionRepository productVersionRepository;

	@Override
	public long count() {
		return productVersionRepository.count();
	}

	@Override
	public <S extends ProductVersion> long count(Example<S> example) {
		return productVersionRepository.count(example);
	}

	@Override
	public Page<ProductVersion> findByProduct(Long productId, Pageable pageable) {
		return productVersionRepository.findByProduct(productId, pageable);
	}

	@Override
	public void delete(ProductVersion entity) {
		productVersionRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		productVersionRepository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<? extends ProductVersion> entities) {
		productVersionRepository.deleteAll(entities);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productVersionRepository.deleteAllById(ids);
	}

	@Override
	public <S extends ProductVersion> S saveAndFlush(S entity) {
		return productVersionRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<ProductVersion> entities) {
		productVersionRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<ProductVersion> entities) {
		productVersionRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productVersionRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		productVersionRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		productVersionRepository.deleteById(id);
	}

	@Override
	public <S extends ProductVersion> boolean exists(Example<S> example) {
		return productVersionRepository.exists(example);
	}

	@Override
	public boolean existsById(Long id) {
		return productVersionRepository.existsById(id);
	}

	@Override
	public List<ProductVersion> findAll() {
		return productVersionRepository.findAll();
	}

	@Override
	public <S extends ProductVersion> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productVersionRepository.findAll(example, pageable);
	}

	@Override
	public <S extends ProductVersion> List<S> findAll(Example<S> example) {
		return productVersionRepository.findAll(example);
	}

	@Override
	public <S extends ProductVersion> List<S> findAll(Example<S> example, Sort sort) {
		return productVersionRepository.findAll(example, sort);
	}

	@Override
	public Page<ProductVersion> findAll(Pageable pageable) {
		return productVersionRepository.findAll(pageable);
	}

	@Override
	public List<ProductVersion> findAll(Sort sort) {
		return productVersionRepository.findAll(sort);
	}

	@Override
	public List<ProductVersion> findAllById(Iterable<Long> ids) {
		return productVersionRepository.findAllById(ids);
	}

	@Override
	public <S extends ProductVersion, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productVersionRepository.findBy(example, queryFunction);
	}

	@Override
	public Optional<ProductVersion> findById(Long id) {
		return productVersionRepository.findById(id);
	}

	@Override
	public <S extends ProductVersion> Optional<S> findOne(Example<S> example) {
		return productVersionRepository.findOne(example);
	}

	@Override
	public void flush() {
		productVersionRepository.flush();
	}

	@Override
	public <S extends ProductVersion> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productVersionRepository.saveAllAndFlush(entities);
	}

	@Override
	public ProductVersion getOne(Long id) {
		return productVersionRepository.getOne(id);
	}

	@Override
	public ProductVersion getById(Long id) {
		return productVersionRepository.getById(id);
	}

	@Override
	public Page<ProductVersion> findByProduct(Long productId, String keyword, Pageable pageable) {
		return productVersionRepository.findByProduct(productId, keyword, pageable);
	}

	@Override
	public ProductVersion getReferenceById(Long id) {
		return productVersionRepository.getReferenceById(id);
	}

	@Override
	public <S extends ProductVersion> S save(S entity) {
		return productVersionRepository.save(entity);
	}

	@Override
	public <S extends ProductVersion> List<S> saveAll(Iterable<S> entities) {
		return productVersionRepository.saveAll(entities);
	}
	
	
}
