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

import com.vandu.dto.MenuDto;
import com.vandu.dto.SubMenuDto;
import com.vandu.model.Menu;
import com.vandu.repository.MenuRepository;
import com.vandu.service.FileSystemStorageService;
import com.vandu.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private FileSystemStorageService fileSystemStorageService;
	
	
	@Override
	public Menu saveOrUpdate(MenuDto menuDto) {
		Menu menu = new Menu();
		
		BeanUtils.copyProperties(menuDto, menu);
		
		if(menuDto.getUrlImage()==null) {
			if(menuDto.getId()!=null) {
				Menu dbMenu = findById(menuDto.getId()).orElse(null);
				
				if(dbMenu!=null) {
					menu.setImage(dbMenu.getImage());
				}
			}
		}else {
			UUID uuid = UUID.randomUUID();
			
			String name = fileSystemStorageService.getStorageFileName(menuDto.getUrlImage(), uuid.toString());
			fileSystemStorageService.saveFile(menuDto.getUrlImage(), name);
			menu.setImage(name);
			
		}
		
		return save(menu);
	}
	
	@Override
	public MenuDto convertToMenuDto(Menu menu) {
		MenuDto menuDto = new MenuDto();
		
		BeanUtils.copyProperties(menu, menuDto);
		
		if(menu.getSubMenus()!=null) {
			List<SubMenuDto> listSubMenuDtos = menu.getSubMenus().stream().map(item->{
				SubMenuDto subMenuDto = new SubMenuDto();
				
				BeanUtils.copyProperties(item, subMenuDto);
				
				return subMenuDto;
			}).toList();
			
			menuDto.setSubMenus(listSubMenuDtos);
		}
		
		return menuDto;
	}
	
	@Override
	public <S extends Menu> S save(S entity) {
		return menuRepository.save(entity);
	}

	@Override
	public <S extends Menu> List<S> saveAll(Iterable<S> entities) {
		return menuRepository.saveAll(entities);
	}

	@Override
	public <S extends Menu> Optional<S> findOne(Example<S> example) {
		return menuRepository.findOne(example);
	}

	@Override
	public List<Menu> findAll(Sort sort) {
		return menuRepository.findAll(sort);
	}

	@Override
	public void flush() {
		menuRepository.flush();
	}

	@Override
	public Page<Menu> findAll(Pageable pageable) {
		return menuRepository.findAll(pageable);
	}

	@Override
	public <S extends Menu> S saveAndFlush(S entity) {
		return menuRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Menu> List<S> saveAllAndFlush(Iterable<S> entities) {
		return menuRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Menu> findAll() {
		return menuRepository.findAll();
	}

	@Override
	public List<Menu> findAllById(Iterable<Long> ids) {
		return menuRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Menu> entities) {
		menuRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Menu> Page<S> findAll(Example<S> example, Pageable pageable) {
		return menuRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Menu> findById(Long id) {
		return menuRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Menu> entities) {
		menuRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return menuRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		menuRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Menu> long count(Example<S> example) {
		return menuRepository.count(example);
	}

	@Override
	public <S extends Menu> boolean exists(Example<S> example) {
		return menuRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		menuRepository.deleteAllInBatch();
	}

	@Override
	public Menu getOne(Long id) {
		return menuRepository.getOne(id);
	}

	@Override
	public <S extends Menu, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return menuRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return menuRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		menuRepository.deleteById(id);
	}

	@Override
	public Menu getById(Long id) {
		return menuRepository.getById(id);
	}

	@Override
	public void delete(Menu entity) {
		menuRepository.delete(entity);
	}

	@Override
	public Menu getReferenceById(Long id) {
		return menuRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		menuRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Menu> entities) {
		menuRepository.deleteAll(entities);
	}

	@Override
	public <S extends Menu> List<S> findAll(Example<S> example) {
		return menuRepository.findAll(example);
	}

	@Override
	public <S extends Menu> List<S> findAll(Example<S> example, Sort sort) {
		return menuRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		menuRepository.deleteAll();
	}


	
	
}
