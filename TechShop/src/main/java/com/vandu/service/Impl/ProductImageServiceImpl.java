package com.vandu.service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vandu.model.Product;
import com.vandu.model.ProductImage;
import com.vandu.repository.ProductImageRepository;
import com.vandu.service.FileSystemStorageService;
import com.vandu.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService{
	
	@Autowired
	private ProductImageRepository productImageRepository;
	
	@Autowired 
	private FileSystemStorageService fileSystemStorageService;
	
	
	@Override
	public void saveImages(List<MultipartFile> images,Product product) {
		
		if(product.getProductImages()!=null) {
			product.getProductImages().forEach(item->{
				try {
					fileSystemStorageService.delete(item.getImageUrl());
					deleteById(item.getId());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		if(images !=null) {
			images.stream().forEach(item ->{
				UUID uuidRand = UUID.randomUUID();
				
				ProductImage productImage = new ProductImage();
				
				String name = fileSystemStorageService.getStorageFileName(item, uuidRand.toString());
				fileSystemStorageService.saveFile(item, name);
				productImage.setImageUrl(name);
				productImage.setProduct(product);
				save(productImage);
			});
		}
	}

	@Override
	public <S extends ProductImage> S save(S entity) {
		return productImageRepository.save(entity);
	}

	@Override
	public <S extends ProductImage> List<S> saveAll(Iterable<S> entities) {
		return productImageRepository.saveAll(entities);
	}

	@Override
	public <S extends ProductImage> Optional<S> findOne(Example<S> example) {
		return productImageRepository.findOne(example);
	}

	@Override
	public List<ProductImage> findAll(Sort sort) {
		return productImageRepository.findAll(sort);
	}

	@Override
	public void flush() {
		productImageRepository.flush();
	}

	@Override
	public Page<ProductImage> findAll(Pageable pageable) {
		return productImageRepository.findAll(pageable);
	}

	@Override
	public <S extends ProductImage> S saveAndFlush(S entity) {
		return productImageRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends ProductImage> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productImageRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<ProductImage> findAll() {
		return productImageRepository.findAll();
	}

	@Override
	public List<ProductImage> findAllById(Iterable<Long> ids) {
		return productImageRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<ProductImage> entities) {
		productImageRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends ProductImage> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productImageRepository.findAll(example, pageable);
	}

	@Override
	public Optional<ProductImage> findById(Long id) {
		return productImageRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<ProductImage> entities) {
		productImageRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return productImageRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productImageRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends ProductImage> long count(Example<S> example) {
		return productImageRepository.count(example);
	}

	@Override
	public <S extends ProductImage> boolean exists(Example<S> example) {
		return productImageRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		productImageRepository.deleteAllInBatch();
	}

	@Override
	public ProductImage getOne(Long id) {
		return productImageRepository.getOne(id);
	}

	@Override
	public <S extends ProductImage, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productImageRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return productImageRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		productImageRepository.deleteById(id);
	}

	@Override
	public ProductImage getById(Long id) {
		return productImageRepository.getById(id);
	}

	@Override
	public void delete(ProductImage entity) {
		productImageRepository.delete(entity);
	}

	@Override
	public ProductImage getReferenceById(Long id) {
		return productImageRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productImageRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends ProductImage> entities) {
		productImageRepository.deleteAll(entities);
	}

	@Override
	public <S extends ProductImage> List<S> findAll(Example<S> example) {
		return productImageRepository.findAll(example);
	}

	@Override
	public <S extends ProductImage> List<S> findAll(Example<S> example, Sort sort) {
		return productImageRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		productImageRepository.deleteAll();
	}
	
	

}
