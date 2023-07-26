package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.dto.MenuDto;
import com.vandu.model.Menu;

public interface MenuService {

	void deleteAll();

	<S extends Menu> List<S> findAll(Example<S> example, Sort sort);

	<S extends Menu> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Menu> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	Menu getReferenceById(Long id);

	void delete(Menu entity);

	Menu getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends Menu, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Menu getOne(Long id);

	void deleteAllInBatch();

	<S extends Menu> boolean exists(Example<S> example);

	<S extends Menu> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<Menu> entities);

	Optional<Menu> findById(Long id);

	<S extends Menu> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Menu> entities);

	List<Menu> findAllById(Iterable<Long> ids);

	List<Menu> findAll();

	<S extends Menu> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Menu> S saveAndFlush(S entity);

	Page<Menu> findAll(Pageable pageable);

	void flush();

	List<Menu> findAll(Sort sort);

	<S extends Menu> Optional<S> findOne(Example<S> example);

	<S extends Menu> List<S> saveAll(Iterable<S> entities);

	<S extends Menu> S save(S entity);

	Menu saveOrUpdate(MenuDto menuDto);

	MenuDto convertToMenuDto(Menu menu);

}
