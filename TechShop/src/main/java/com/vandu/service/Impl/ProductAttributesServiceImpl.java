package com.vandu.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.vandu.dto.AttributesDto;
import com.vandu.dto.ProductDto;
import com.vandu.model.Attributes;
import com.vandu.model.CategoryAttributes;
import com.vandu.model.Product;
import com.vandu.model.ProductAttributes;
import com.vandu.repository.ProductAttributesReposiory;
import com.vandu.repository.ProductRepository;
import com.vandu.service.ProductAttributesService;

@Service
public class ProductAttributesServiceImpl implements ProductAttributesService{
	
	@Override
	public ProductAttributes findByProductAndAttributes(Long productId, Long attributesId) {
		return productAttributesReposiory.findByProductAndAttributes(productId, attributesId);
	}

	
	@Override
	public void addToAttributes(List<AttributesDto> list,Product product) {
		if(list!=null && list.size()>0) {
			list.stream().forEach(item->{
				Attributes attributes = new Attributes();
				attributes.setId(item.getId());
				
				ProductAttributes productAttributes = new ProductAttributes();
				productAttributes.setAttributes(attributes);
				productAttributes.setProduct(product);
				
				productAttributes.setValue(item.getName());
				
				productAttributesReposiory.save(productAttributes);
				
			});
		}
	}
	
	@Override
	public void saveOrUpdateAttributes(Product dbProduct,ProductDto productDto,Product entity) {
		System.err.println("hshgggggg p");
		
		if(dbProduct==null) {
			System.err.println("hshgggggg p");
			addToAttributes(productDto.getListAttributes(), entity);
		}else {
			List<Attributes> listAttributes = dbProduct.getCategory().getCategoryAttributes().stream().map(CategoryAttributes::getAttributes).toList(); 
			deleteAttributesByCategory(listAttributes, dbProduct);
			System.err.println("dẫ caihdsgaf");
			addToAttributes(productDto.getListAttributes(), entity);
			
		}
	}
	
	@Override
	public void deleteAttributesByCategory(List<Attributes> list,Product product) {
		list.stream().forEach(item->{
			ProductAttributes productAttributes = findByProductAndAttributes(product.getProductId(), item.getId());
			
			if(productAttributes!=null) {
				delete(productAttributes);
			}
		});
	}
	
	@Override
	public Page<ProductAttributes> findAllByProduct(Long productId, Pageable pageable) {
		return productAttributesReposiory.findAllByProduct(productId, pageable);
	}

	@Override
	public <S extends ProductAttributes> S save(S entity) {
		return productAttributesReposiory.save(entity);
	}

	@Override
	public <S extends ProductAttributes> List<S> saveAll(Iterable<S> entities) {
		return productAttributesReposiory.saveAll(entities);
	}

	@Override
	public <S extends ProductAttributes> Optional<S> findOne(Example<S> example) {
		return productAttributesReposiory.findOne(example);
	}

	@Override
	public List<ProductAttributes> findAll(Sort sort) {
		return productAttributesReposiory.findAll(sort);
	}

	@Override
	public void flush() {
		productAttributesReposiory.flush();
	}

	@Override
	public Page<ProductAttributes> findAll(Pageable pageable) {
		return productAttributesReposiory.findAll(pageable);
	}

	@Override
	public <S extends ProductAttributes> S saveAndFlush(S entity) {
		return productAttributesReposiory.saveAndFlush(entity);
	}

	@Override
	public <S extends ProductAttributes> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productAttributesReposiory.saveAllAndFlush(entities);
	}

	@Override
	public List<ProductAttributes> findAll() {
		return productAttributesReposiory.findAll();
	}

	@Override
	public List<ProductAttributes> findAllById(Iterable<Long> ids) {
		return productAttributesReposiory.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<ProductAttributes> entities) {
		productAttributesReposiory.deleteInBatch(entities);
	}

	@Override
	public <S extends ProductAttributes> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productAttributesReposiory.findAll(example, pageable);
	}

	@Override
	public Optional<ProductAttributes> findById(Long id) {
		return productAttributesReposiory.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<ProductAttributes> entities) {
		productAttributesReposiory.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return productAttributesReposiory.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productAttributesReposiory.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends ProductAttributes> long count(Example<S> example) {
		return productAttributesReposiory.count(example);
	}

	@Override
	public <S extends ProductAttributes> boolean exists(Example<S> example) {
		return productAttributesReposiory.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		productAttributesReposiory.deleteAllInBatch();
	}

	@Override
	public ProductAttributes getOne(Long id) {
		return productAttributesReposiory.getOne(id);
	}

	@Override
	public <S extends ProductAttributes, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productAttributesReposiory.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return productAttributesReposiory.count();
	}

	@Override
	public void deleteById(Long id) {
		productAttributesReposiory.deleteById(id);
	}

	@Override
	public ProductAttributes getById(Long id) {
		return productAttributesReposiory.getById(id);
	}

	@Override
	public void delete(ProductAttributes entity) {
		productAttributesReposiory.delete(entity);
	}

	@Override
	public ProductAttributes getReferenceById(Long id) {
		return productAttributesReposiory.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productAttributesReposiory.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends ProductAttributes> entities) {
		productAttributesReposiory.deleteAll(entities);
	}

	@Override
	public <S extends ProductAttributes> List<S> findAll(Example<S> example) {
		return productAttributesReposiory.findAll(example);
	}

	@Override
	public <S extends ProductAttributes> List<S> findAll(Example<S> example, Sort sort) {
		return productAttributesReposiory.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		productAttributesReposiory.deleteAll();
	}

	@Autowired
	private ProductAttributesReposiory productAttributesReposiory;
	
	

	
	
}
