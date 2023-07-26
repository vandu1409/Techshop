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

import com.vandu.model.CategoryAttributes;
import com.vandu.repository.CategoryAttributesRepository;
import com.vandu.service.CategoryAttributesService;

@Service
public class CategoryAttributesServiceImpl implements CategoryAttributesService {

	@Override
	public Page<CategoryAttributes> findAllAtrributesByCategory(Long id, Pageable pageable) {
		return categoryAttributesRepository.findAllAtrributesByCategory(id, pageable);
	}

	@Override
	public CategoryAttributes findByCategoryAndAttributes(Long cid, Long aid) {
		return categoryAttributesRepository.findByCategoryAndAttributes(cid, aid);
	}

	@Override
	public Page<CategoryAttributes> findAllByCategory(Long id, Pageable pageable) {
		return categoryAttributesRepository.findAllByCategory(id, pageable);
	}

	@Autowired
	private CategoryAttributesRepository categoryAttributesRepository;

	@Override
	public <S extends CategoryAttributes> S save(S entity) {
		return categoryAttributesRepository.save(entity);
	}

	@Override
	public <S extends CategoryAttributes> List<S> saveAll(Iterable<S> entities) {
		return categoryAttributesRepository.saveAll(entities);
	}

//	@Override
//	public Page<CategoryAttributes> findAllByProduct(Long productId, Pageable pageable) {
//		return categoryAttributesRepository.findAllByProduct(productId, pageable);
//	}

	@Override
	public <S extends CategoryAttributes> Optional<S> findOne(Example<S> example) {
		return categoryAttributesRepository.findOne(example);
	}

	@Override
	public List<CategoryAttributes> findAll(Sort sort) {
		return categoryAttributesRepository.findAll(sort);
	}

	@Override
	public void flush() {
		categoryAttributesRepository.flush();
	}

	@Override
	public Page<CategoryAttributes> findAll(Pageable pageable) {
		return categoryAttributesRepository.findAll(pageable);
	}

	@Override
	public <S extends CategoryAttributes> S saveAndFlush(S entity) {
		return categoryAttributesRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends CategoryAttributes> List<S> saveAllAndFlush(Iterable<S> entities) {
		return categoryAttributesRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<CategoryAttributes> findAll() {
		return categoryAttributesRepository.findAll();
	}

	@Override
	public List<CategoryAttributes> findAllById(Iterable<Long> ids) {
		return categoryAttributesRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<CategoryAttributes> entities) {
		categoryAttributesRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends CategoryAttributes> Page<S> findAll(Example<S> example, Pageable pageable) {
		return categoryAttributesRepository.findAll(example, pageable);
	}

	@Override
	public Optional<CategoryAttributes> findById(Long id) {
		return categoryAttributesRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<CategoryAttributes> entities) {
		categoryAttributesRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return categoryAttributesRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		categoryAttributesRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends CategoryAttributes> long count(Example<S> example) {
		return categoryAttributesRepository.count(example);
	}

	@Override
	public <S extends CategoryAttributes> boolean exists(Example<S> example) {
		return categoryAttributesRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		categoryAttributesRepository.deleteAllInBatch();
	}

	@Override
	public CategoryAttributes getOne(Long id) {
		return categoryAttributesRepository.getOne(id);
	}

	@Override
	public <S extends CategoryAttributes, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return categoryAttributesRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return categoryAttributesRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		categoryAttributesRepository.deleteById(id);
	}

	@Override
	public CategoryAttributes getById(Long id) {
		return categoryAttributesRepository.getById(id);
	}

	@Override
	public void delete(CategoryAttributes entity) {
		categoryAttributesRepository.delete(entity);
	}

	@Override
	public CategoryAttributes getReferenceById(Long id) {
		return categoryAttributesRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		categoryAttributesRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends CategoryAttributes> entities) {
		categoryAttributesRepository.deleteAll(entities);
	}

	@Override
	public <S extends CategoryAttributes> List<S> findAll(Example<S> example) {
		return categoryAttributesRepository.findAll(example);
	}

	@Override
	public <S extends CategoryAttributes> List<S> findAll(Example<S> example, Sort sort) {
		return categoryAttributesRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		categoryAttributesRepository.deleteAll();
	}
	
	
}
