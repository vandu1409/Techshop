package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.dto.BrandDto;
import com.vandu.model.Brand;
import com.vandu.model.Category;

public interface BrandService {

	<S extends Brand> S saveAndFlush(S entity);

	<S extends Brand> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Brand> List<S> saveAll(Iterable<S> entities);

	<S extends Brand> S save(S entity);

	Brand getReferenceById(Long id);

	Brand getOne(Long arg0);

	Brand getById(Long arg0);

	void flush();

	<S extends Brand> Optional<S> findOne(Example<S> example);

	Optional<Brand> findById(Long id);

	<S extends Brand, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	List<Brand> findAllById(Iterable<Long> ids);

	List<Brand> findAll(Sort sort);

	Page<Brand> findAll(Pageable pageable);

	<S extends Brand> List<S> findAll(Example<S> example);

	<S extends Brand> List<S> findAll(Example<S> example, Sort sort);

	<S extends Brand> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Brand> findAll();

	boolean existsById(Long id);

	<S extends Brand> boolean exists(Example<S> example);

	void deleteInBatch(Iterable<Brand> entities);

	void deleteById(Long id);

	void deleteAllInBatch(Iterable<Brand> entities);

	void deleteAllInBatch();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends Brand> entities);

	void deleteAll();

	void delete(Brand entity);

	<S extends Brand> long count(Example<S> example);

	long count();

	Page<Brand> findByNameContaining(String name, Pageable pageable);

	Optional<Brand> findByBrandCode(String brandCode);

	Page<Brand> findAllByCategoryAndKeyword(Long id, String keyword, Pageable pageable);

	Page<Brand> findAllByCategory(Long id, Pageable pageable);

	BrandDto findByBrandDtoById(Long id);

	List<Brand> findAllByCategory(Long id);

	List<Brand> findAllByCategoryCode(String categoryCode);


	Brand saveOrUpdateBrand(BrandDto brandDto, Category category);

}
