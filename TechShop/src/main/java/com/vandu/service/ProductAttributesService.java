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

import com.vandu.dto.AttributesDto;
import com.vandu.dto.ProductDto;
import com.vandu.model.Attributes;
import com.vandu.model.Product;
import com.vandu.model.ProductAttributes;

public interface ProductAttributesService {

	void deleteAll();

	<S extends ProductAttributes> List<S> findAll(Example<S> example, Sort sort);

	<S extends ProductAttributes> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends ProductAttributes> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	ProductAttributes getReferenceById(Long id);

	void delete(ProductAttributes entity);

	ProductAttributes getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends ProductAttributes, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	ProductAttributes getOne(Long id);

	void deleteAllInBatch();

	<S extends ProductAttributes> boolean exists(Example<S> example);

	<S extends ProductAttributes> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<ProductAttributes> entities);

	Optional<ProductAttributes> findById(Long id);

	<S extends ProductAttributes> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<ProductAttributes> entities);

	List<ProductAttributes> findAllById(Iterable<Long> ids);

	List<ProductAttributes> findAll();

	<S extends ProductAttributes> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends ProductAttributes> S saveAndFlush(S entity);

	Page<ProductAttributes> findAll(Pageable pageable);

	void flush();

	List<ProductAttributes> findAll(Sort sort);

	<S extends ProductAttributes> Optional<S> findOne(Example<S> example);

	<S extends ProductAttributes> List<S> saveAll(Iterable<S> entities);

	<S extends ProductAttributes> S save(S entity);

	Page<ProductAttributes> findAllByProduct(Long productId, Pageable pageable);

	ProductAttributes findByProductAndAttributes(Long productId, Long attributesId);

	void addToAttributes(List<AttributesDto> list, Product product);

	void deleteAttributesByCategory(List<Attributes> list, Product product);

	void saveOrUpdateAttributes(Product dbProduct, ProductDto productDto, Product entity);

	

}
