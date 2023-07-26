package com.vandu.service.Impl;

import java.util.Date;
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

import com.vandu.dto.ProductDetailsDto;
import com.vandu.model.Color;
import com.vandu.model.ProductDetails;
import com.vandu.model.ProductVersion;
import com.vandu.repository.ProductDetailsRepository;
import com.vandu.repository.ProductRepository;
import com.vandu.service.FileSystemStorageService;
import com.vandu.service.ProductDetailsService;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
	@Autowired
	private ProductDetailsRepository productDetailsRepository;
	
	@Autowired
	private FileSystemStorageService fileSystemStorageService;

	@Override
	public long count() {
		return productDetailsRepository.count();
	}
	
	@Override
	public ProductDetails saveOrUpdateProductDetalis(ProductDetailsDto productDetailsDto,ProductVersion productVersion) {
		ProductDetails productDetails = new ProductDetails();
		
		BeanUtils.copyProperties(productDetailsDto, productDetails);
		
	    if (productDetailsDto.getFileImgae() == null && productDetailsDto.getProductDetailsId() != null) {
	        ProductDetails dbProductDetails = findById(productDetailsDto.getProductDetailsId()).orElse(null);
	        if (dbProductDetails != null) {
	            productDetails.setImage(dbProductDetails.getImage());
	        }
	    } else if (productDetailsDto.getFileImgae() != null) {
	        UUID uuid = UUID.randomUUID();
	        String name = fileSystemStorageService.getStorageFileName(productDetailsDto.getFileImgae(), uuid.toString());
	        fileSystemStorageService.saveFile(productDetailsDto.getFileImgae(), name);
	        productDetails.setImage(name);
	    }

	 
	    if(productDetailsDto.getProductDetailsId()!=null) {
	    	productDetails.setUpdateDate(new Date());
	    }else {
	    	productDetails.setCreateDate(new Date());
	    }
	    
	    Color color = new Color();
	    color.setId(productDetailsDto.getColorId());

	    productDetails.setColor(color);
	    productDetails.setProductVersion(productVersion);
	    
		return save(productDetails);
	}

	@Override
	public List<Color> findColorByVersion(Long id) {
		return productDetailsRepository.findColorByVersion(id);
	}

	@Override
	public <S extends ProductDetails> long count(Example<S> example) {
		return productDetailsRepository.count(example);
	}

	@Override
	public void delete(ProductDetails entity) {
		productDetailsRepository.delete(entity);
	}

	@Override
	public Page<ProductDetails> findByVersion(Long id, Pageable pageable) {
		return productDetailsRepository.findByVersion(id, pageable);
	}

	@Override
	public Page<ProductDetails> findByVersion(Long id, String colorName, Pageable pageable) {
		return productDetailsRepository.findByVersion(id, colorName, pageable);
	}

	@Override
	public void deleteAll() {
		productDetailsRepository.deleteAll();
	}

	@Override
	public ProductDetails findByVersionAndColor(Long productVersionId, Long colorId) {
		return productDetailsRepository.findByVersionAndColor(productVersionId, colorId);
	}

	@Override
	public void deleteAll(Iterable<? extends ProductDetails> entities) {
		productDetailsRepository.deleteAll(entities);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		productDetailsRepository.deleteAllById(ids);
	}

	@Override
	public <S extends ProductDetails> S saveAndFlush(S entity) {
		return productDetailsRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<ProductDetails> entities) {
		productDetailsRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<ProductDetails> entities) {
		productDetailsRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		productDetailsRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		productDetailsRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		productDetailsRepository.deleteById(id);
	}

	@Override
	public <S extends ProductDetails> boolean exists(Example<S> example) {
		return productDetailsRepository.exists(example);
	}

	@Override
	public boolean existsById(Long id) {
		return productDetailsRepository.existsById(id);
	}

	@Override
	public List<ProductDetails> findAll() {
		return productDetailsRepository.findAll();
	}

	@Override
	public <S extends ProductDetails> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productDetailsRepository.findAll(example, pageable);
	}

	@Override
	public <S extends ProductDetails> List<S> findAll(Example<S> example) {
		return productDetailsRepository.findAll(example);
	}

	@Override
	public <S extends ProductDetails> List<S> findAll(Example<S> example, Sort sort) {
		return productDetailsRepository.findAll(example, sort);
	}

	@Override
	public Page<ProductDetails> findAll(Pageable pageable) {
		return productDetailsRepository.findAll(pageable);
	}

	@Override
	public List<ProductDetails> findAll(Sort sort) {
		return productDetailsRepository.findAll(sort);
	}

	@Override
	public List<ProductDetails> findAllById(Iterable<Long> ids) {
		return productDetailsRepository.findAllById(ids);
	}

	@Override
	public <S extends ProductDetails, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productDetailsRepository.findBy(example, queryFunction);
	}

	@Override
	public Optional<ProductDetails> findById(Long id) {
		return productDetailsRepository.findById(id);
	}

	@Override
	public <S extends ProductDetails> Optional<S> findOne(Example<S> example) {
		return productDetailsRepository.findOne(example);
	}

	@Override
	public void flush() {
		productDetailsRepository.flush();
	}

	@Override
	public <S extends ProductDetails> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productDetailsRepository.saveAllAndFlush(entities);
	}

	@Override
	public ProductDetails getOne(Long id) {
		return productDetailsRepository.getOne(id);
	}

	@Override
	public ProductDetails getById(Long id) {
		return productDetailsRepository.getById(id);
	}

	@Override
	public ProductDetails getReferenceById(Long id) {
		return productDetailsRepository.getReferenceById(id);
	}

	@Override
	public <S extends ProductDetails> S save(S entity) {
		return productDetailsRepository.save(entity);
	}

	@Override
	public <S extends ProductDetails> List<S> saveAll(Iterable<S> entities) {
		return productDetailsRepository.saveAll(entities);
	}
}
