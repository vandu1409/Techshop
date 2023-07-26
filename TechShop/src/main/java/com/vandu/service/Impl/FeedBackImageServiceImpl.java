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

import com.vandu.model.FeedBackImage;
import com.vandu.repository.FeedBackImageRepository;
import com.vandu.service.FeedBackImageService;

@Service
public class FeedBackImageServiceImpl implements FeedBackImageService {
	
	@Autowired
	private FeedBackImageRepository feedBackImageRepository;

	@Override
	public <S extends FeedBackImage> S save(S entity) {
		return feedBackImageRepository.save(entity);
	}

	@Override
	public <S extends FeedBackImage> List<S> saveAll(Iterable<S> entities) {
		return feedBackImageRepository.saveAll(entities);
	}

	@Override
	public <S extends FeedBackImage> Optional<S> findOne(Example<S> example) {
		return feedBackImageRepository.findOne(example);
	}

	@Override
	public List<FeedBackImage> findAll(Sort sort) {
		return feedBackImageRepository.findAll(sort);
	}

	@Override
	public void flush() {
		feedBackImageRepository.flush();
	}

	@Override
	public Page<FeedBackImage> findAll(Pageable pageable) {
		return feedBackImageRepository.findAll(pageable);
	}

	@Override
	public <S extends FeedBackImage> S saveAndFlush(S entity) {
		return feedBackImageRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends FeedBackImage> List<S> saveAllAndFlush(Iterable<S> entities) {
		return feedBackImageRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<FeedBackImage> findAll() {
		return feedBackImageRepository.findAll();
	}

	@Override
	public List<FeedBackImage> findAllById(Iterable<Long> ids) {
		return feedBackImageRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<FeedBackImage> entities) {
		feedBackImageRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends FeedBackImage> Page<S> findAll(Example<S> example, Pageable pageable) {
		return feedBackImageRepository.findAll(example, pageable);
	}

	@Override
	public Optional<FeedBackImage> findById(Long id) {
		return feedBackImageRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<FeedBackImage> entities) {
		feedBackImageRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return feedBackImageRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		feedBackImageRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends FeedBackImage> long count(Example<S> example) {
		return feedBackImageRepository.count(example);
	}

	@Override
	public <S extends FeedBackImage> boolean exists(Example<S> example) {
		return feedBackImageRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		feedBackImageRepository.deleteAllInBatch();
	}

	@Override
	public FeedBackImage getOne(Long id) {
		return feedBackImageRepository.getOne(id);
	}

	@Override
	public <S extends FeedBackImage, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return feedBackImageRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return feedBackImageRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		feedBackImageRepository.deleteById(id);
	}

	@Override
	public FeedBackImage getById(Long id) {
		return feedBackImageRepository.getById(id);
	}

	@Override
	public void delete(FeedBackImage entity) {
		feedBackImageRepository.delete(entity);
	}

	@Override
	public FeedBackImage getReferenceById(Long id) {
		return feedBackImageRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		feedBackImageRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends FeedBackImage> entities) {
		feedBackImageRepository.deleteAll(entities);
	}

	@Override
	public <S extends FeedBackImage> List<S> findAll(Example<S> example) {
		return feedBackImageRepository.findAll(example);
	}

	@Override
	public <S extends FeedBackImage> List<S> findAll(Example<S> example, Sort sort) {
		return feedBackImageRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		feedBackImageRepository.deleteAll();
	}


	
	
	
}
