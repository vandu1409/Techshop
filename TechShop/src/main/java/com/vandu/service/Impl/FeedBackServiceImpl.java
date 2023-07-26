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

import com.vandu.model.FeedBack;
import com.vandu.repository.FeatureRepository;
import com.vandu.repository.FeedBackRepository;
import com.vandu.service.FeedBackService;

@Service
public class FeedBackServiceImpl implements FeedBackService{
	
	@Override
	public Page<FeedBack> findAllByProduct(String productCode, Pageable pageable) {
		return feedBackRepository.findAllByProduct(productCode, pageable);
	}

	@Autowired
	private FeedBackRepository feedBackRepository;
	
	
	@Override
	public <S extends FeedBack> S save(S entity) {
		return feedBackRepository.save(entity);
	}

	@Override
	public <S extends FeedBack> List<S> saveAll(Iterable<S> entities) {
		return feedBackRepository.saveAll(entities);
	}

	@Override
	public <S extends FeedBack> Optional<S> findOne(Example<S> example) {
		return feedBackRepository.findOne(example);
	}

	@Override
	public List<FeedBack> findAll(Sort sort) {
		return feedBackRepository.findAll(sort);
	}

	@Override
	public void flush() {
		feedBackRepository.flush();
	}

	@Override
	public Page<FeedBack> findAll(Pageable pageable) {
		return feedBackRepository.findAll(pageable);
	}

	@Override
	public <S extends FeedBack> S saveAndFlush(S entity) {
		return feedBackRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends FeedBack> List<S> saveAllAndFlush(Iterable<S> entities) {
		return feedBackRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<FeedBack> findAll() {
		return feedBackRepository.findAll();
	}

	@Override
	public List<FeedBack> findAllById(Iterable<Long> ids) {
		return feedBackRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<FeedBack> entities) {
		feedBackRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends FeedBack> Page<S> findAll(Example<S> example, Pageable pageable) {
		return feedBackRepository.findAll(example, pageable);
	}

	@Override
	public Optional<FeedBack> findById(Long id) {
		return feedBackRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<FeedBack> entities) {
		feedBackRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return feedBackRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		feedBackRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends FeedBack> long count(Example<S> example) {
		return feedBackRepository.count(example);
	}

	@Override
	public <S extends FeedBack> boolean exists(Example<S> example) {
		return feedBackRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		feedBackRepository.deleteAllInBatch();
	}

	@Override
	public FeedBack getOne(Long id) {
		return feedBackRepository.getOne(id);
	}

	@Override
	public <S extends FeedBack, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return feedBackRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return feedBackRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		feedBackRepository.deleteById(id);
	}

	@Override
	public FeedBack getById(Long id) {
		return feedBackRepository.getById(id);
	}

	@Override
	public void delete(FeedBack entity) {
		feedBackRepository.delete(entity);
	}

	@Override
	public FeedBack getReferenceById(Long id) {
		return feedBackRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		feedBackRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends FeedBack> entities) {
		feedBackRepository.deleteAll(entities);
	}

	@Override
	public <S extends FeedBack> List<S> findAll(Example<S> example) {
		return feedBackRepository.findAll(example);
	}

	@Override
	public <S extends FeedBack> List<S> findAll(Example<S> example, Sort sort) {
		return feedBackRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		feedBackRepository.deleteAll();
	}

	
	

}
