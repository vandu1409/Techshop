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

import com.vandu.dto.ProductSeriesDto;
import com.vandu.model.Brand;
import com.vandu.model.ProductSeries;
import com.vandu.repository.ProductSeriesRepository;
import com.vandu.service.FileSystemStorageService;
import com.vandu.service.ProductSeriesService;

@Service
public class ProductSeriesServiceImpl implements ProductSeriesService{
	@Autowired
	private ProductSeriesRepository productSeriesRepository;
	
	@Autowired
	private FileSystemStorageService fileSystemStorageService;

	
	
	@Override
	public ProductSeries saveOrUpdateSeries(ProductSeriesDto productSeriesDto,Brand brand) {
		ProductSeries productSeries = new ProductSeries();
		
		BeanUtils.copyProperties(productSeriesDto, productSeries);
		
		if(productSeriesDto.getImage()==null) {
			
			if(productSeries.getProductSeriesId()!=null) {
				ProductSeries dbSeries = findById(productSeries.getProductSeriesId()).orElse(null);
				
				if(dbSeries!=null) {
					productSeries.setImage(dbSeries.getImage());
				}
			}
		}else {
			UUID uuid = UUID.randomUUID();
			String name = fileSystemStorageService.getStorageFileName(productSeriesDto.getImage(),uuid.toString());
			fileSystemStorageService.saveFile(productSeriesDto.getImage(), name);
			productSeries.setImage(name);
			
		}
		
		productSeries.setBrand(brand);
		
		return save(productSeries);

	}
	
	@Override
	public long count() {
		return productSeriesRepository.count();
	}

	@Override
	public <S extends ProductSeries> long count(Example<S> example) {
		return productSeriesRepository.count(example);
	}

	@Override
	public List<ProductSeries> findAllByBrandCode(String brandCode) {
		return productSeriesRepository.findAllByBrandCode(brandCode);
	}

	@Override
	public List<ProductSeries> findAllByBrand(Long brandId) {
		return productSeriesRepository.findAllByBrand(brandId);
	}

	@Override
	public Optional<ProductSeries> findBySeriesCode(String seriesCode) {
		return productSeriesRepository.findBySeriesCode(seriesCode);
	}

	@Override
	public void delete(ProductSeries entity) {
		productSeriesRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		productSeriesRepository.deleteAll();
	}

	

	@Override
	public Page<ProductSeries> findAllByBrand(Long brandId, String name, Pageable pageable) {
		return productSeriesRepository.findAllByBrand(brandId, name, pageable);
	}

	@Override
	public void deleteAll(Iterable<? extends ProductSeries> entities) {
		productSeriesRepository.deleteAll(entities);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productSeriesRepository.deleteAllById(ids);
	}

	@Override
	public <S extends ProductSeries> S saveAndFlush(S entity) {
		return productSeriesRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<ProductSeries> entities) {
		productSeriesRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<ProductSeries> entities) {
		productSeriesRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productSeriesRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		productSeriesRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		productSeriesRepository.deleteById(id);
	}

	@Override
	public <S extends ProductSeries> boolean exists(Example<S> example) {
		return productSeriesRepository.exists(example);
	}

	@Override
	public boolean existsById(Long id) {
		return productSeriesRepository.existsById(id);
	}

	@Override
	public List<ProductSeries> findAll() {
		return productSeriesRepository.findAll();
	}

	@Override
	public <S extends ProductSeries> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productSeriesRepository.findAll(example, pageable);
	}

	@Override
	public <S extends ProductSeries> List<S> findAll(Example<S> example) {
		return productSeriesRepository.findAll(example);
	}

	@Override
	public <S extends ProductSeries> List<S> findAll(Example<S> example, Sort sort) {
		return productSeriesRepository.findAll(example, sort);
	}

	@Override
	public Page<ProductSeries> findAll(Pageable pageable) {
		return productSeriesRepository.findAll(pageable);
	}

	@Override
	public List<ProductSeries> findAll(Sort sort) {
		return productSeriesRepository.findAll(sort);
	}

	@Override
	public List<ProductSeries> findAllById(Iterable<Long> ids) {
		return productSeriesRepository.findAllById(ids);
	}

	@Override
	public <S extends ProductSeries, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productSeriesRepository.findBy(example, queryFunction);
	}

	@Override
	public Page<ProductSeries> findAllByBrand(Long brandId, Pageable pageable) {
		return productSeriesRepository.findAllByBrand(brandId, pageable);
	}

	@Override
	public Optional<ProductSeries> findById(Long id) {
		return productSeriesRepository.findById(id);
	}

	@Override
	public <S extends ProductSeries> Optional<S> findOne(Example<S> example) {
		return productSeriesRepository.findOne(example);
	}

	@Override
	public void flush() {
		productSeriesRepository.flush();
	}

	@Override
	public <S extends ProductSeries> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productSeriesRepository.saveAllAndFlush(entities);
	}

	@Override
	public ProductSeries getOne(Long id) {
		return productSeriesRepository.getOne(id);
	}

	@Override
	public ProductSeries getById(Long id) {
		return productSeriesRepository.getById(id);
	}

	@Override
	public ProductSeries getReferenceById(Long id) {
		return productSeriesRepository.getReferenceById(id);
	}

	@Override
	public <S extends ProductSeries> S save(S entity) {
		return productSeriesRepository.save(entity);
	}

	@Override
	public <S extends ProductSeries> List<S> saveAll(Iterable<S> entities) {
		return productSeriesRepository.saveAll(entities);
	}
	
	
}
