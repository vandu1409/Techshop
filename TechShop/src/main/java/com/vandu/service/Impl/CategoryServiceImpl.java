package com.vandu.service.Impl;

import java.util.ArrayList;
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

import com.vandu.dto.CategoryDto;
import com.vandu.dto.response.BrandResponseDto;
import com.vandu.dto.response.CategoryResponseDto;
import com.vandu.dto.response.ProductSeriesResponseDto;
import com.vandu.model.Category;
import com.vandu.repository.CategoryRepository;
import com.vandu.service.CategoryService;
import com.vandu.service.FileSystemStorageService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private FileSystemStorageService fileSystemStorageService;

	@Override
	public <S extends Category> S save(S entity) {
		return categoryRepository.save(entity);
	}

	@Override
	public CategoryResponseDto fromCategoryResponseDto(Category category) {
		CategoryResponseDto categoryResponseDto = new CategoryResponseDto();

		BeanUtils.copyProperties(category, categoryResponseDto);

		List<BrandResponseDto> listBrandResponseDto = new ArrayList<>();

		if (category.getBrands() != null) {
			category.getBrands().stream().forEach(item -> {

				BrandResponseDto brandResponseDto = new BrandResponseDto();
				BeanUtils.copyProperties(item, brandResponseDto);

				if (item.getProductSeries() != null) {
					List<ProductSeriesResponseDto> listSeries = item.getProductSeries().stream().map(series -> {
						ProductSeriesResponseDto productSeriesResponseDto = new ProductSeriesResponseDto();
						BeanUtils.copyProperties(series, productSeriesResponseDto);

						return productSeriesResponseDto;
					}).toList();

					brandResponseDto.setSeries(listSeries);
				}

				listBrandResponseDto.add(brandResponseDto);
			});

		}
		categoryResponseDto.setBrands(listBrandResponseDto);

		return categoryResponseDto;

	}

	@Override
	public Category saveOrUpdateCategory(CategoryDto categoryDto) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryDto, category);

		if (categoryDto.getImage() == null) {

			if (categoryDto.getId() != null) {
				Category dBCategory = findById(categoryDto.getId()).orElse(null);

				if (dBCategory != null) {
					category.setImage(dBCategory.getImage());
				}

			}

		} else {
			UUID uuid = UUID.randomUUID();
			String name = fileSystemStorageService.getStorageFileName(categoryDto.getImage(), uuid.toString());
			fileSystemStorageService.saveFile(categoryDto.getImage(), name);
			category.setImage(name);
		}

		return categoryRepository.save(category);
	}

	@Override
	public Page<Category> findByNameContaining(String name, Pageable pageable) {
		return categoryRepository.findByNameContaining(name, pageable);
	}

	@Override
	public Optional<Category> findByCategoryCode(String categoryCode) {
		return categoryRepository.findByCategoryCode(categoryCode);
	}

	@Override
	public <S extends Category> List<S> saveAll(Iterable<S> entities) {
		return categoryRepository.saveAll(entities);
	}

	@Override
	public <S extends Category> Optional<S> findOne(Example<S> example) {
		return categoryRepository.findOne(example);
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoryRepository.findAll(sort);
	}

	@Override
	public void flush() {
		categoryRepository.flush();
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	@Override
	public <S extends Category> S saveAndFlush(S entity) {
		return categoryRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
		return categoryRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> findAllById(Iterable<Long> ids) {
		return categoryRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Category> entities) {
		categoryRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
		return categoryRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Category> entities) {
		categoryRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return categoryRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		categoryRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Category> long count(Example<S> example) {
		return categoryRepository.count(example);
	}

	@Override
	public <S extends Category> boolean exists(Example<S> example) {
		return categoryRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		categoryRepository.deleteAllInBatch();
	}

	@Override
	public Category getOne(Long id) {
		return categoryRepository.getOne(id);
	}

	@Override
	public <S extends Category, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return categoryRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public Category getById(Long id) {
		return categoryRepository.getById(id);
	}

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public Category getReferenceById(Long id) {
		return categoryRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		categoryRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Category> entities) {
		categoryRepository.deleteAll(entities);
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example) {
		return categoryRepository.findAll(example);
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
		return categoryRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}

}
