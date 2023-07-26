package com.vandu.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.vandu.dto.SubMenuDto;
import com.vandu.model.Menu;
import com.vandu.model.SubMenu;

public interface SubMenuService {

	void deleteAll();

	<S extends SubMenu> List<S> findAll(Example<S> example, Sort sort);

	<S extends SubMenu> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends SubMenu> entities);

	void deleteAllById(Iterable<? extends Long> ids);

	SubMenu getReferenceById(Long id);

	void delete(SubMenu entity);

	SubMenu getById(Long id);

	void deleteById(Long id);

	long count();

	<S extends SubMenu, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	SubMenu getOne(Long id);

	void deleteAllInBatch();

	<S extends SubMenu> boolean exists(Example<S> example);

	<S extends SubMenu> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	boolean existsById(Long id);

	void deleteAllInBatch(Iterable<SubMenu> entities);

	Optional<SubMenu> findById(Long id);

	<S extends SubMenu> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<SubMenu> entities);

	List<SubMenu> findAllById(Iterable<Long> ids);

	List<SubMenu> findAll();

	<S extends SubMenu> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends SubMenu> S saveAndFlush(S entity);

	Page<SubMenu> findAll(Pageable pageable);

	void flush();

	List<SubMenu> findAll(Sort sort);

	<S extends SubMenu> Optional<S> findOne(Example<S> example);

	<S extends SubMenu> List<S> saveAll(Iterable<S> entities);

	<S extends SubMenu> S save(S entity);

	SubMenu saveOrUpdate(SubMenuDto subMenuDto, Menu menu);

}
