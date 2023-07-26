package com.vandu.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.vandu.helper.UserHelper;
import com.vandu.model.User;
import com.vandu.model.WishList;
import com.vandu.repository.WishListRepository;
import com.vandu.service.WishListService;

@Service
public class WishListServiceImpl implements WishListService{

	@Autowired
	private WishListRepository wishListRepository;
	


	
	@Override
	public void deleteWishlist(Long id,User user) {
		
		WishList wishList = wishListRepository.findByUserAndProduct(user.getUserId(), id).orElse(null);
		
		if(wishList!=null) {
			wishListRepository.delete(wishList);
		}
	}
	
	@Override
	public <S extends WishList> S save(S entity) {
		return wishListRepository.save(entity);
	}

	@Override
	public List<WishList> findByUser(Long uid) {
		return wishListRepository.findByUser(uid);
	}

	@Override
	public <S extends WishList> List<S> saveAll(Iterable<S> entities) {
		return wishListRepository.saveAll(entities);
	}

	@Override
	public <S extends WishList> Optional<S> findOne(Example<S> example) {
		return wishListRepository.findOne(example);
	}

	@Override
	public List<WishList> findAll(Sort sort) {
		return wishListRepository.findAll(sort);
	}

	@Override
	public Optional<WishList> findByUserAndProduct(Long uid, Long pid) {
		return wishListRepository.findByUserAndProduct(uid, pid);
	}

	@Override
	public void flush() {
		wishListRepository.flush();
	}

	@Override
	public Page<WishList> findAll(Pageable pageable) {
		return wishListRepository.findAll(pageable);
	}

	@Override
	public <S extends WishList> S saveAndFlush(S entity) {
		return wishListRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends WishList> List<S> saveAllAndFlush(Iterable<S> entities) {
		return wishListRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<WishList> findAll() {
		return wishListRepository.findAll();
	}

	@Override
	public List<WishList> findAllById(Iterable<Long> ids) {
		return wishListRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<WishList> entities) {
		wishListRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends WishList> Page<S> findAll(Example<S> example, Pageable pageable) {
		return wishListRepository.findAll(example, pageable);
	}

	@Override
	public Optional<WishList> findById(Long id) {
		return wishListRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<WishList> entities) {
		wishListRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return wishListRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		wishListRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends WishList> long count(Example<S> example) {
		return wishListRepository.count(example);
	}

	@Override
	public <S extends WishList> boolean exists(Example<S> example) {
		return wishListRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		wishListRepository.deleteAllInBatch();
	}

	@Override
	public WishList getOne(Long id) {
		return wishListRepository.getOne(id);
	}

	@Override
	public <S extends WishList, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return wishListRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return wishListRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		wishListRepository.deleteById(id);
	}

	@Override
	public WishList getById(Long id) {
		return wishListRepository.getById(id);
	}

	@Override
	public void delete(WishList entity) {
		wishListRepository.delete(entity);
	}

	@Override
	public WishList getReferenceById(Long id) {
		return wishListRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		wishListRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends WishList> entities) {
		wishListRepository.deleteAll(entities);
	}

	@Override
	public <S extends WishList> List<S> findAll(Example<S> example) {
		return wishListRepository.findAll(example);
	}

	@Override
	public <S extends WishList> List<S> findAll(Example<S> example, Sort sort) {
		return wishListRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		wishListRepository.deleteAll();
	}
	
	
}
