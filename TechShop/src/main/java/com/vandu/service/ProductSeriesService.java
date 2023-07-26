package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.dto.ProductSeriesDto;
import com.vandu.model.Brand;
import com.vandu.model.ProductSeries;

public interface ProductSeriesService {

	<S extends ProductSeries> List<S> saveAll(Iterable<S> entities);

	<S extends ProductSeries> S save(S entity);

	ProductSeries getReferenceById(Long id);

	ProductSeries getById(Long id);

	ProductSeries getOne(Long id);

	<S extends ProductSeries> List<S> saveAllAndFlush(Iterable<S> entities);

	void flush();

	<S extends ProductSeries> Optional<S> findOne(Example<S> example);

	Optional<ProductSeries> findById(Long id);

	Page<ProductSeries> findAllByBrand(Long brandId, Pageable pageable);

	<S extends ProductSeries, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	List<ProductSeries> findAllById(Iterable<Long> ids);

	List<ProductSeries> findAll(Sort sort);

	Page<ProductSeries> findAll(Pageable pageable);

	<S extends ProductSeries> List<S> findAll(Example<S> example, Sort sort);

	<S extends ProductSeries> List<S> findAll(Example<S> example);

	<S extends ProductSeries> Page<S> findAll(Example<S> example, Pageable pageable);

	List<ProductSeries> findAll();

	boolean existsById(Long id);

	<S extends ProductSeries> boolean exists(Example<S> example);

	void deleteById(Long id);

	void deleteAllInBatch();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllInBatch(Iterable<ProductSeries> entities);

	void deleteInBatch(Iterable<ProductSeries> entities);

	<S extends ProductSeries> S saveAndFlush(S entity);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends ProductSeries> entities);

	Page<ProductSeries> findAllByBrand(Long brandId, String name, Pageable pageable);

	void deleteAll();

	void delete(ProductSeries entity);

	<S extends ProductSeries> long count(Example<S> example);

	long count();

	List<ProductSeries> findAllByBrand(Long brandId);

	Optional<ProductSeries> findBySeriesCode(String seriesCode);

	List<ProductSeries> findAllByBrandCode(String brandCode);

	ProductSeries saveOrUpdateSeries(ProductSeriesDto productSeriesDto, Brand brand);

	

}
