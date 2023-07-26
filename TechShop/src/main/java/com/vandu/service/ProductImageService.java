package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.web.multipart.MultipartFile;

import com.vandu.model.Product;
import com.vandu.model.ProductImage;

public interface ProductImageService {

	void deleteAll();

	<S extends ProductImage> List<S> findAll(Example<S> example, Sort sort);

	<S extends ProductImage> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends ProductImage> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	ProductImage getReferenceById(Long id);

	void delete(ProductImage entity);

	ProductImage getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends ProductImage, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	ProductImage getOne(Long id);

	void deleteAllInBatch();

	<S extends ProductImage> boolean exists(Example<S> example);

	<S extends ProductImage> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<ProductImage> entities);

	Optional<ProductImage> findById(Long id);

	<S extends ProductImage> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<ProductImage> entities);

	List<ProductImage> findAllById(Iterable<Long> ids);

	List<ProductImage> findAll();

	<S extends ProductImage> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends ProductImage> S saveAndFlush(S entity);

	Page<ProductImage> findAll(Pageable pageable);

	void flush();

	List<ProductImage> findAll(Sort sort);

	<S extends ProductImage> Optional<S> findOne(Example<S> example);

	<S extends ProductImage> List<S> saveAll(Iterable<S> entities);

	<S extends ProductImage> S save(S entity);

	void saveImages(List<MultipartFile> images, Product product);

}
