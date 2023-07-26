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

import com.vandu.model.Feature;
import com.vandu.repository.FeatureRepository;
import com.vandu.service.FeatureService;
@Service
public class FeatureServiceImpl implements FeatureService{

	@Autowired
	private FeatureRepository featureRepository;

	@Override
	public <S extends Feature> S save(S entity) {
		return featureRepository.save(entity);
	}

	@Override
	public <S extends Feature> List<S> saveAll(Iterable<S> entities) {
		return featureRepository.saveAll(entities);
	}

	@Override
	public <S extends Feature> Optional<S> findOne(Example<S> example) {
		return featureRepository.findOne(example);
	}

	@Override
	public List<Feature> findAll(Sort sort) {
		return featureRepository.findAll(sort);
	}

	@Override
	public void flush() {
		featureRepository.flush();
	}

	@Override
	public Page<Feature> findAll(Pageable pageable) {
		return featureRepository.findAll(pageable);
	}

	@Override
	public <S extends Feature> S saveAndFlush(S entity) {
		return featureRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Feature> List<S> saveAllAndFlush(Iterable<S> entities) {
		return featureRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Feature> findAll() {
		return featureRepository.findAll();
	}

	@Override
	public List<Feature> findAllById(Iterable<Long> ids) {
		return featureRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Feature> entities) {
		featureRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Feature> Page<S> findAll(Example<S> example, Pageable pageable) {
		return featureRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Feature> findById(Long id) {
		return featureRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Feature> entities) {
		featureRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return featureRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		featureRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Feature> long count(Example<S> example) {
		return featureRepository.count(example);
	}

	@Override
	public <S extends Feature> boolean exists(Example<S> example) {
		return featureRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		featureRepository.deleteAllInBatch();
	}

	@Override
	public Feature getOne(Long id) {
		return featureRepository.getOne(id);
	}

	@Override
	public <S extends Feature, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return featureRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return featureRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		featureRepository.deleteById(id);
	}

	@Override
	public Feature getById(Long id) {
		return featureRepository.getById(id);
	}

	@Override
	public void delete(Feature entity) {
		featureRepository.delete(entity);
	}

	@Override
	public Feature getReferenceById(Long id) {
		return featureRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		featureRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Feature> entities) {
		featureRepository.deleteAll(entities);
	}

	@Override
	public <S extends Feature> List<S> findAll(Example<S> example) {
		return featureRepository.findAll(example);
	}

	@Override
	public <S extends Feature> List<S> findAll(Example<S> example, Sort sort) {
		return featureRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		featureRepository.deleteAll();
	}
	
	
}
