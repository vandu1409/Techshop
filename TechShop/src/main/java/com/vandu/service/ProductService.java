package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.dto.ProductDto;
import com.vandu.dto.response.ProductResponseDto;
import com.vandu.model.Product;

public interface ProductService {

	<S extends Product> List<S> saveAll(Iterable<S> entities);

	<S extends Product> S save(S entity);

	Product getReferenceById(Long id);

	Product getById(Long id);

	Product getOne(Long id);

	<S extends Product> List<S> saveAllAndFlush(Iterable<S> entities);

	void flush();

	<S extends Product> Optional<S> findOne(Example<S> example);

	Optional<Product> findById(Long id);

	<S extends Product, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	List<Product> findAllById(Iterable<Long> ids);

	List<Product> findAll(Sort sort);

	Page<Product> findAll(Pageable pageable);

	<S extends Product> List<S> findAll(Example<S> example, Sort sort);

	<S extends Product> List<S> findAll(Example<S> example);

	<S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Product> findAll();

	boolean existsById(Long id);

	<S extends Product> boolean exists(Example<S> example);

	void deleteById(Long id);

	void deleteAllInBatch();

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllInBatch(Iterable<Product> entities);

	void deleteInBatch(Iterable<Product> entities);

	<S extends Product> S saveAndFlush(S entity);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends Product> entities);

	void deleteAll();

	void delete(Product entity);

	<S extends Product> long count(Example<S> example);

	long count();

	Page<Product> findByNameContaining(String name, Pageable pageable);

	Page<Product> findAll(Specification<Product> spec, Pageable pageable);

	Optional<Product> findByProductCode(String productCode);

	List<Product> findRandomProducts(int limit);

	Boolean checkDuplicateCode(String code);

	List<Product> findRandomByCategoryCode(int n, String categoryCode);

	Page<Product> findByKeyword(String key, Pageable pageable);

	Product saveOrUpdate(ProductDto productDto);

	ProductResponseDto convertToProductResponseDto(Product product);

	List<Product> findByKeyword(String key);



}
