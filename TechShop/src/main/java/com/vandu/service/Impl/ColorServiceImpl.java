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

import com.vandu.model.Color;
import com.vandu.repository.ColorRepository;
import com.vandu.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService{
	@Autowired
	private ColorRepository colorRepository;

	@Override
	public long count() {
		return colorRepository.count();
	}

	@Override
	public <S extends Color> long count(Example<S> example) {
		return colorRepository.count(example);
	}

	@Override
	public void delete(Color entity) {
		colorRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		colorRepository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<? extends Color> entities) {
		colorRepository.deleteAll(entities);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		colorRepository.deleteAllById(ids);
	}

	@Override
	public <S extends Color> S saveAndFlush(S entity) {
		return colorRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<Color> entities) {
		colorRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<Color> entities) {
		colorRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		colorRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		colorRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		colorRepository.deleteById(id);
	}

	@Override
	public <S extends Color> boolean exists(Example<S> example) {
		return colorRepository.exists(example);
	}

	@Override
	public Page<Color> findByNameContaining(String name, Pageable pageable) {
		return colorRepository.findByNameContaining(name, pageable);
	}

	@Override
	public boolean existsById(Long id) {
		return colorRepository.existsById(id);
	}

	@Override
	public List<Color> findAll() {
		return colorRepository.findAll();
	}

	@Override
	public <S extends Color> Page<S> findAll(Example<S> example, Pageable pageable) {
		return colorRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Color> List<S> findAll(Example<S> example) {
		return colorRepository.findAll(example);
	}

	@Override
	public <S extends Color> List<S> findAll(Example<S> example, Sort sort) {
		return colorRepository.findAll(example, sort);
	}

	@Override
	public Page<Color> findAll(Pageable pageable) {
		return colorRepository.findAll(pageable);
	}

	@Override
	public List<Color> findAll(Sort sort) {
		return colorRepository.findAll(sort);
	}

	@Override
	public List<Color> findAllById(Iterable<Long> ids) {
		return colorRepository.findAllById(ids);
	}

	@Override
	public <S extends Color, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return colorRepository.findBy(example, queryFunction);
	}

	@Override
	public Optional<Color> findById(Long id) {
		return colorRepository.findById(id);
	}

	@Override
	public <S extends Color> Optional<S> findOne(Example<S> example) {
		return colorRepository.findOne(example);
	}

	@Override
	public void flush() {
		colorRepository.flush();
	}

	@Override
	public <S extends Color> List<S> saveAllAndFlush(Iterable<S> entities) {
		return colorRepository.saveAllAndFlush(entities);
	}

	@Override
	public Color getOne(Long id) {
		return colorRepository.getOne(id);
	}

	@Override
	public Color getById(Long id) {
		return colorRepository.getById(id);
	}

	@Override
	public Color getReferenceById(Long id) {
		return colorRepository.getReferenceById(id);
	}

	@Override
	public <S extends Color> S save(S entity) {
		return colorRepository.save(entity);
	}

	@Override
	public <S extends Color> List<S> saveAll(Iterable<S> entities) {
		return colorRepository.saveAll(entities);
	}

}
