package com.vandu.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.vandu.dto.DescriptionDto;
import com.vandu.model.Description;
import com.vandu.model.Product;
import com.vandu.repository.DescriptionRespository;
import com.vandu.service.DescriptionService;
import com.vandu.service.FileSystemStorageService;

@Service
public class DescriptionServiceImpl implements DescriptionService {

	@Autowired
	private DescriptionRespository descriptionRespository;

	@Autowired
	private FileSystemStorageService fileSystemStorageService;

	@Override
	public Description saveOrUpdate(DescriptionDto descriptionDto, Product product) {
		Description description = new Description();
		BeanUtils.copyProperties(descriptionDto, description);

		if (descriptionDto.getImage() == null && descriptionDto.getId() != null) {
			Description dbDescription = findById(descriptionDto.getId()).orElse(null);

			if (description != null) {
				description.setImage(dbDescription.getImage());
			}
		} else if (descriptionDto.getImage() != null) {
			UUID uuid = UUID.randomUUID();
			String name = fileSystemStorageService.getStorageFileName(descriptionDto.getImage(), uuid.toString());
			fileSystemStorageService.saveFile(descriptionDto.getImage(), name);
			description.setImage(name);
		}

		description.setProduct(product);

		return save(description);
	}

	@Override
	public <S extends Description> S save(S entity) {
		return descriptionRespository.save(entity);
	}

	@Override
	public <S extends Description> List<S> saveAll(Iterable<S> entities) {
		return descriptionRespository.saveAll(entities);
	}

	@Override
	public <S extends Description> Optional<S> findOne(Example<S> example) {
		return descriptionRespository.findOne(example);
	}

	@Override
	public List<Description> findAll(Sort sort) {
		return descriptionRespository.findAll(sort);
	}

	@Override
	public void flush() {
		descriptionRespository.flush();
	}

	@Override
	public Page<Description> findAll(Pageable pageable) {
		return descriptionRespository.findAll(pageable);
	}

	@Override
	public <S extends Description> S saveAndFlush(S entity) {
		return descriptionRespository.saveAndFlush(entity);
	}

	@Override
	public <S extends Description> List<S> saveAllAndFlush(Iterable<S> entities) {
		return descriptionRespository.saveAllAndFlush(entities);
	}

	@Override
	public List<Description> findAll() {
		return descriptionRespository.findAll();
	}

	@Override
	public List<Description> findAllById(Iterable<Long> ids) {
		return descriptionRespository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Description> entities) {
		descriptionRespository.deleteInBatch(entities);
	}

	@Override
	public <S extends Description> Page<S> findAll(Example<S> example, Pageable pageable) {
		return descriptionRespository.findAll(example, pageable);
	}

	@Override
	public Optional<Description> findById(Long id) {
		return descriptionRespository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Description> entities) {
		descriptionRespository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return descriptionRespository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		descriptionRespository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Description> long count(Example<S> example) {
		return descriptionRespository.count(example);
	}

	@Override
	public <S extends Description> boolean exists(Example<S> example) {
		return descriptionRespository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		descriptionRespository.deleteAllInBatch();
	}

	@Override
	public Description getOne(Long id) {
		return descriptionRespository.getOne(id);
	}

	@Override
	public <S extends Description, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return descriptionRespository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return descriptionRespository.count();
	}

	@Override
	public void deleteById(Long id) {
		descriptionRespository.deleteById(id);
	}

	@Override
	public Description getById(Long id) {
		return descriptionRespository.getById(id);
	}

	@Override
	public void delete(Description entity) {
		descriptionRespository.delete(entity);
	}

	@Override
	public Description getReferenceById(Long id) {
		return descriptionRespository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		descriptionRespository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Description> entities) {
		descriptionRespository.deleteAll(entities);
	}

	@Override
	public <S extends Description> List<S> findAll(Example<S> example) {
		return descriptionRespository.findAll(example);
	}

	@Override
	public <S extends Description> List<S> findAll(Example<S> example, Sort sort) {
		return descriptionRespository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		descriptionRespository.deleteAll();
	}

}
