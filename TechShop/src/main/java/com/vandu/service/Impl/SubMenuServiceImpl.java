package com.vandu.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.vandu.dto.SubMenuDto;
import com.vandu.model.Menu;
import com.vandu.model.SubMenu;
import com.vandu.repository.SubMenuRepository;
import com.vandu.service.SubMenuService;

@Service
public class SubMenuServiceImpl implements SubMenuService{
	
	@Autowired
	private SubMenuRepository subMenuRepository;
	
	
	@Override
	public SubMenu saveOrUpdate(SubMenuDto subMenuDto ,Menu menu) {
		SubMenu subMenu = new SubMenu();
		
		BeanUtils.copyProperties(subMenuDto, subMenu);
		subMenu.setMenu(menu);
		
		return save(subMenu);
	}

	@Override
	public <S extends SubMenu> S save(S entity) {
		return subMenuRepository.save(entity);
	}

	@Override
	public <S extends SubMenu> List<S> saveAll(Iterable<S> entities) {
		return subMenuRepository.saveAll(entities);
	}

	@Override
	public <S extends SubMenu> Optional<S> findOne(Example<S> example) {
		return subMenuRepository.findOne(example);
	}

	@Override
	public List<SubMenu> findAll(Sort sort) {
		return subMenuRepository.findAll(sort);
	}

	@Override
	public void flush() {
		subMenuRepository.flush();
	}

	@Override
	public Page<SubMenu> findAll(Pageable pageable) {
		return subMenuRepository.findAll(pageable);
	}

	@Override
	public <S extends SubMenu> S saveAndFlush(S entity) {
		return subMenuRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends SubMenu> List<S> saveAllAndFlush(Iterable<S> entities) {
		return subMenuRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<SubMenu> findAll() {
		return subMenuRepository.findAll();
	}

	@Override
	public List<SubMenu> findAllById(Iterable<Long> ids) {
		return subMenuRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<SubMenu> entities) {
		subMenuRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends SubMenu> Page<S> findAll(Example<S> example, Pageable pageable) {
		return subMenuRepository.findAll(example, pageable);
	}

	@Override
	public Optional<SubMenu> findById(Long id) {
		return subMenuRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<SubMenu> entities) {
		subMenuRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return subMenuRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		subMenuRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends SubMenu> long count(Example<S> example) {
		return subMenuRepository.count(example);
	}

	@Override
	public <S extends SubMenu> boolean exists(Example<S> example) {
		return subMenuRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		subMenuRepository.deleteAllInBatch();
	}

	@Override
	public SubMenu getOne(Long id) {
		return subMenuRepository.getOne(id);
	}

	@Override
	public <S extends SubMenu, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return subMenuRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return subMenuRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		subMenuRepository.deleteById(id);
	}

	@Override
	public SubMenu getById(Long id) {
		return subMenuRepository.getById(id);
	}

	@Override
	public void delete(SubMenu entity) {
		subMenuRepository.delete(entity);
	}

	@Override
	public SubMenu getReferenceById(Long id) {
		return subMenuRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		subMenuRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends SubMenu> entities) {
		subMenuRepository.deleteAll(entities);
	}

	@Override
	public <S extends SubMenu> List<S> findAll(Example<S> example) {
		return subMenuRepository.findAll(example);
	}

	@Override
	public <S extends SubMenu> List<S> findAll(Example<S> example, Sort sort) {
		return subMenuRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		subMenuRepository.deleteAll();
	}

	
	
	
}
