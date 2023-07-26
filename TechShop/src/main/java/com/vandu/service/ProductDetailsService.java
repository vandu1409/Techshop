package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.dto.ProductDetailsDto;
import com.vandu.model.Color;
import com.vandu.model.ProductDetails;
import com.vandu.model.ProductVersion;

public interface ProductDetailsService {

	<S extends ProductDetails> List<S> saveAll(Iterable<S> entities);

	<S extends ProductDetails> S save(S entity);

	ProductDetails getReferenceById(Long id);

	ProductDetails getById(Long id);

	ProductDetails getOne(Long id);

	<S extends ProductDetails> List<S> saveAllAndFlush(Iterable<S> entities);

	void flush();

	<S extends ProductDetails> Optional<S> findOne(Example<S> example);

	Optional<ProductDetails> findById(Long id);

	<S extends ProductDetails, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	List<ProductDetails> findAllById(Iterable<Long> ids);

	List<ProductDetails> findAll(Sort sort);

	Page<ProductDetails> findAll(Pageable pageable);

	<S extends ProductDetails> List<S> findAll(Example<S> example, Sort sort);

	<S extends ProductDetails> List<S> findAll(Example<S> example);

	<S extends ProductDetails> Page<S> findAll(Example<S> example, Pageable pageable);

	List<ProductDetails> findAll();

	boolean existsById(Long id);

	<S extends ProductDetails> boolean exists(Example<S> example);

	void deleteById(Long id);

	void deleteAllInBatch();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllInBatch(Iterable<ProductDetails> entities);

	void deleteInBatch(Iterable<ProductDetails> entities);

	<S extends ProductDetails> S saveAndFlush(S entity);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends ProductDetails> entities);

	void deleteAll();

	void delete(ProductDetails entity);

	<S extends ProductDetails> long count(Example<S> example);

	long count();

	Page<ProductDetails> findByVersion(Long id, String colorName, Pageable pageable);

	Page<ProductDetails> findByVersion(Long id, Pageable pageable);

	List<Color> findColorByVersion(Long id);

	ProductDetails findByVersionAndColor(Long productVersionId, Long colorId);

	ProductDetails saveOrUpdateProductDetalis(ProductDetailsDto productDetailsDto, ProductVersion productVersion);

}
