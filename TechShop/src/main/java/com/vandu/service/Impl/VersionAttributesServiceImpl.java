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

import com.vandu.model.VersionAttributes;
import com.vandu.repository.VersionAttributesRepository;
import com.vandu.service.VersionAttributesService;

@Service
public class VersionAttributesServiceImpl implements VersionAttributesService{
	
	@Autowired
	private VersionAttributesRepository versionAttributesRepository;

	@Override
	public <S extends VersionAttributes> S save(S entity) {
		return versionAttributesRepository.save(entity);
	}

	@Override
	public Page<VersionAttributes> getAllByVersion(Long id, Pageable pageable) {
		return versionAttributesRepository.getAllByVersion(id, pageable);
	}

	@Override
	public <S extends VersionAttributes> List<S> saveAll(Iterable<S> entities) {
		return versionAttributesRepository.saveAll(entities);
	}

	@Override
	public <S extends VersionAttributes> Optional<S> findOne(Example<S> example) {
		return versionAttributesRepository.findOne(example);
	}

	@Override
	public List<VersionAttributes> findAll(Sort sort) {
		return versionAttributesRepository.findAll(sort);
	}

	@Override
	public void flush() {
		versionAttributesRepository.flush();
	}

	@Override
	public Page<VersionAttributes> findAll(Pageable pageable) {
		return versionAttributesRepository.findAll(pageable);
	}

	@Override
	public <S extends VersionAttributes> S saveAndFlush(S entity) {
		return versionAttributesRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends VersionAttributes> List<S> saveAllAndFlush(Iterable<S> entities) {
		return versionAttributesRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<VersionAttributes> findAll() {
		return versionAttributesRepository.findAll();
	}

	@Override
	public List<VersionAttributes> findAllById(Iterable<Long> ids) {
		return versionAttributesRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<VersionAttributes> entities) {
		versionAttributesRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends VersionAttributes> Page<S> findAll(Example<S> example, Pageable pageable) {
		return versionAttributesRepository.findAll(example, pageable);
	}

	@Override
	public Optional<VersionAttributes> findById(Long id) {
		return versionAttributesRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<VersionAttributes> entities) {
		versionAttributesRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return versionAttributesRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		versionAttributesRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends VersionAttributes> long count(Example<S> example) {
		return versionAttributesRepository.count(example);
	}

	@Override
	public <S extends VersionAttributes> boolean exists(Example<S> example) {
		return versionAttributesRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		versionAttributesRepository.deleteAllInBatch();
	}

	@Override
	public VersionAttributes getOne(Long id) {
		return versionAttributesRepository.getOne(id);
	}

	@Override
	public <S extends VersionAttributes, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return versionAttributesRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return versionAttributesRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		versionAttributesRepository.deleteById(id);
	}

	@Override
	public VersionAttributes getById(Long id) {
		return versionAttributesRepository.getById(id);
	}

	@Override
	public void delete(VersionAttributes entity) {
		versionAttributesRepository.delete(entity);
	}

	@Override
	public VersionAttributes getReferenceById(Long id) {
		return versionAttributesRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		versionAttributesRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends VersionAttributes> entities) {
		versionAttributesRepository.deleteAll(entities);
	}

	@Override
	public <S extends VersionAttributes> List<S> findAll(Example<S> example) {
		return versionAttributesRepository.findAll(example);
	}

	@Override
	public <S extends VersionAttributes> List<S> findAll(Example<S> example, Sort sort) {
		return versionAttributesRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		versionAttributesRepository.deleteAll();
	}
	
	
}
