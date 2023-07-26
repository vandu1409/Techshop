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

import com.vandu.dto.BrandDto;
import com.vandu.dto.response.CategoryResponseDto;
import com.vandu.model.Brand;
import com.vandu.model.Category;
import com.vandu.repository.BrandRepository;
import com.vandu.service.BrandService;
import com.vandu.service.FileSystemStorageService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private FileSystemStorageService fileSystemStorageService;

	@Override
	public long count() {
		return brandRepository.count();
	}

	
	@Override
	public Brand saveOrUpdateBrand(BrandDto brandDto,Category category) {
		Brand brand = new Brand();
		
		BeanUtils.copyProperties(brandDto, brand);
		
		if(brandDto.getImage()==null) {
			
			if(brandDto.getBrandId()!=null) {
				Brand dbBrand = findById(brandDto.getBrandId()).orElse(null);
				
				if(dbBrand!=null) {
					brand.setImage(dbBrand.getImage());
				}
				
			}
			
			
		}else {
			UUID uuid = UUID.randomUUID();
			String name = fileSystemStorageService.getStorageFileName(brandDto.getImage(), uuid.toString());
			fileSystemStorageService.saveFile(brandDto.getImage(), name);
			brand.setImage(name);
		}
		
		brand.setCategory(category);
		
		return save(brand);
	}
	

	
	@Override
	public List<Brand> findAllByCategory(Long id) {
		return brandRepository.findAllByCategory(id);
	}

	@Override
	public List<Brand> findAllByCategoryCode(String categoryCode) {
		return brandRepository.findAllByCategoryCode(categoryCode);
	}

	@Override
	public BrandDto findByBrandDtoById(Long id) {
		Brand brand = findById(id).orElse(null);
		BrandDto brandDto = new BrandDto();
		
		if(brand!=null) {
			BeanUtils.copyProperties(brand,brandDto);
//			brandDto.setCategoryId(brand.getCategory().getId());
		}
		
		return brandDto;
	}
	@Override
	public Page<Brand> findAllByCategory(Long id, Pageable pageable) {
		return brandRepository.findAllByCategory(id, pageable);
	}

	@Override
	public Page<Brand> findAllByCategoryAndKeyword(Long id, String keyword, Pageable pageable) {
		return brandRepository.findAllByCategoryAndKeyword(id, keyword, pageable);
	}

	@Override
	public Optional<Brand> findByBrandCode(String brandCode) {
		return brandRepository.findByBrandCode(brandCode);
	}

	@Override
	public <S extends Brand> long count(Example<S> example) {
		return brandRepository.count(example);
	}

	@Override
	public void delete(Brand entity) {
		brandRepository.delete(entity);
	}

	@Override
	public Page<Brand> findByNameContaining(String name, Pageable pageable) {
		return brandRepository.findByNameContaining(name, pageable);
	}

	@Override
	public void deleteAll() {
		brandRepository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<? extends Brand> entities) {
		brandRepository.deleteAll(entities);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		brandRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		brandRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		brandRepository.deleteAllInBatch();
	}

	@Override
	public void deleteAllInBatch(Iterable<Brand> entities) {
		brandRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteById(Long id) {
		brandRepository.deleteById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Brand> entities) {
		brandRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Brand> boolean exists(Example<S> example) {
		return brandRepository.exists(example);
	}

	@Override
	public boolean existsById(Long id) {
		return brandRepository.existsById(id);
	}

	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	@Override
	public <S extends Brand> Page<S> findAll(Example<S> example, Pageable pageable) {
		return brandRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Brand> List<S> findAll(Example<S> example, Sort sort) {
		return brandRepository.findAll(example, sort);
	}

	@Override
	public <S extends Brand> List<S> findAll(Example<S> example) {
		return brandRepository.findAll(example);
	}

	@Override
	public Page<Brand> findAll(Pageable pageable) {
		return brandRepository.findAll(pageable);
	}

	@Override
	public List<Brand> findAll(Sort sort) {
		return brandRepository.findAll(sort);
	}

	@Override
	public List<Brand> findAllById(Iterable<Long> ids) {
		return brandRepository.findAllById(ids);
	}

	@Override
	public <S extends Brand, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return brandRepository.findBy(example, queryFunction);
	}

	@Override
	public Optional<Brand> findById(Long id) {
		return brandRepository.findById(id);
	}

	@Override
	public <S extends Brand> Optional<S> findOne(Example<S> example) {
		return brandRepository.findOne(example);
	}

	@Override
	public void flush() {
		brandRepository.flush();
	}

	@Override
	public Brand getById(Long arg0) {
		return brandRepository.getById(arg0);
	}

	@Override
	public Brand getOne(Long arg0) {
		return brandRepository.getOne(arg0);
	}

	@Override
	public Brand getReferenceById(Long id) {
		return brandRepository.getReferenceById(id);
	}

	@Override
	public <S extends Brand> S save(S entity) {
		return brandRepository.save(entity);
	}

	@Override
	public <S extends Brand> List<S> saveAll(Iterable<S> entities) {
		return brandRepository.saveAll(entities);
	}

	@Override
	public <S extends Brand> List<S> saveAllAndFlush(Iterable<S> entities) {
		return brandRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Brand> S saveAndFlush(S entity) {
		return brandRepository.saveAndFlush(entity);
	}
	
	
}
