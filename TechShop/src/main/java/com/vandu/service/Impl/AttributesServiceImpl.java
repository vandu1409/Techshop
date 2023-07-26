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

import com.vandu.model.Attributes;
import com.vandu.repository.AttributesRepository;
import com.vandu.service.AttributesService;

@Service
public class AttributesServiceImpl implements AttributesService{
	
	@Autowired
	private AttributesRepository attributesRepository;

	@Override
	public <S extends Attributes> S save(S entity) {
		return attributesRepository.save(entity);
	}

	@Override
	public <S extends Attributes> List<S> saveAll(Iterable<S> entities) {
		return attributesRepository.saveAll(entities);
	}

	@Override
	public <S extends Attributes> Optional<S> findOne(Example<S> example) {
		return attributesRepository.findOne(example);
	}

	@Override
	public List<Attributes> findAll(Sort sort) {
		return attributesRepository.findAll(sort);
	}

	@Override
	public void flush() {
		attributesRepository.flush();
	}

	@Override
	public Page<Attributes> findAll(Pageable pageable) {
		return attributesRepository.findAll(pageable);
	}

	@Override
	public <S extends Attributes> S saveAndFlush(S entity) {
		return attributesRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Attributes> List<S> saveAllAndFlush(Iterable<S> entities) {
		return attributesRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Attributes> findAll() {
		return attributesRepository.findAll();
	}

	@Override
	public List<Attributes> findAllById(Iterable<Long> ids) {
		return attributesRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Attributes> entities) {
		attributesRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Attributes> Page<S> findAll(Example<S> example, Pageable pageable) {
		return attributesRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Attributes> findById(Long id) {
		return attributesRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Attributes> entities) {
		attributesRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return attributesRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		attributesRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Attributes> long count(Example<S> example) {
		return attributesRepository.count(example);
	}

	@Override
	public <S extends Attributes> boolean exists(Example<S> example) {
		return attributesRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		attributesRepository.deleteAllInBatch();
	}

	@Override
	public Attributes getOne(Long id) {
		return attributesRepository.getOne(id);
	}

	@Override
	public <S extends Attributes, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return attributesRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return attributesRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		attributesRepository.deleteById(id);
	}

	@Override
	public Attributes getById(Long id) {
		return attributesRepository.getById(id);
	}

	@Override
	public void delete(Attributes entity) {
		attributesRepository.delete(entity);
	}

	@Override
	public Attributes getReferenceById(Long id) {
		return attributesRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		attributesRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Attributes> entities) {
		attributesRepository.deleteAll(entities);
	}

	@Override
	public <S extends Attributes> List<S> findAll(Example<S> example) {
		return attributesRepository.findAll(example);
	}

	@Override
	public <S extends Attributes> List<S> findAll(Example<S> example, Sort sort) {
		return attributesRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		attributesRepository.deleteAll();
	}
	
	

}
