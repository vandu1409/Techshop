package com.vandu.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.vandu.dto.DiscountDto;
import com.vandu.dto.VoucherDto;
import com.vandu.enums.DiscountType;
import com.vandu.model.Discount;
import com.vandu.model.Voucher;
import com.vandu.repository.DiscountRepository;
import com.vandu.service.DiscountService;

import jakarta.persistence.EntityManager;



@Service
public class DiscountServiceImpl implements DiscountService {
	@Autowired
	private DiscountRepository discountRepository;

	@Override
	public long count() {
		return discountRepository.count();
	}

	@Override
	public <S extends Discount> long count(Example<S> example) {
		return discountRepository.count(example);
	}

	@Override
	public DiscountDto findDiscountDtoById(Long id) {
		Discount discount = findById(id).orElse(null);
		DiscountDto discountDto = new DiscountDto();

		BeanUtils.copyProperties(discount, discountDto);

		discountDto.setDiscountType(discount.getDiscountType() == DiscountType.DISCOUNTBYPRICE ? 1 : 2);

		return discountDto;

	}
	
	@Override
	public Page<Discount> findByStatus(Date today, Pageable pageable, int status) {
		switch (status) {
		case 0:
			return findAll(pageable);
		case 1: // 1 là danh sách voucher còn hạn
			return findValidDiscounts(pageable);
		case 2:
			return findExpiredDiscounts(pageable);
		default:
			throw new IllegalArgumentException("Invalid status value: " + status);
		}

	}

	@Override
	public List<Discount> findValidDicounts() {
		LocalDate now = LocalDate.now();
		return discountRepository.findValidDicounts(now);
		
	}

	
	
	@Override
	public Page<Discount> findValidDiscounts(Pageable pageable) {
	    LocalDate now = LocalDate.now();
	  return discountRepository.findValidDicounts(now, pageable);
	}
	
	@Override
	public Page<Discount> findExpiredDiscounts( Pageable pageable) {
		  LocalDate now = LocalDate.now();
		  return discountRepository.findExpiredDiscounts(now, pageable);
	}

	@Override
	public void delete(Discount entity) {
		discountRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		discountRepository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<? extends Discount> entities) {
		discountRepository.deleteAll(entities);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		discountRepository.deleteAllById(ids);
	}

	@Override
	public <S extends Discount> S saveAndFlush(S entity) {
		return discountRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<Discount> entities) {
		discountRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<Discount> entities) {
		discountRepository.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		discountRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		discountRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(Long id) {
		discountRepository.deleteById(id);
	}

	@Override
	public <S extends Discount> boolean exists(Example<S> example) {
		return discountRepository.exists(example);
	}

	@Override
	public boolean existsById(Long id) {
		return discountRepository.existsById(id);
	}

	@Override
	public List<Discount> findAll() {
		return discountRepository.findAll();
	}

	@Override
	public <S extends Discount> Page<S> findAll(Example<S> example, Pageable pageable) {
		return discountRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Discount> List<S> findAll(Example<S> example) {
		return discountRepository.findAll(example);
	}

	@Override
	public <S extends Discount> List<S> findAll(Example<S> example, Sort sort) {
		return discountRepository.findAll(example, sort);
	}

	@Override
	public Page<Discount> findAll(Pageable pageable) {
		return discountRepository.findAll(pageable);
	}

	@Override
	public List<Discount> findAll(Sort sort) {
		return discountRepository.findAll(sort);
	}

	@Override
	public List<Discount> findAllById(Iterable<Long> ids) {
		return discountRepository.findAllById(ids);
	}

	@Override
	public <S extends Discount, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return discountRepository.findBy(example, queryFunction);
	}

	@Override
	public Optional<Discount> findById(Long id) {
		return discountRepository.findById(id);
	}

	@Override
	public <S extends Discount> Optional<S> findOne(Example<S> example) {
		return discountRepository.findOne(example);
	}

	@Override
	public void flush() {
		discountRepository.flush();
	}

	@Override
	public <S extends Discount> List<S> saveAllAndFlush(Iterable<S> entities) {
		return discountRepository.saveAllAndFlush(entities);
	}

	@Override
	public Discount getOne(Long id) {
		return discountRepository.getOne(id);
	}

	@Override
	public Discount getById(Long id) {
		return discountRepository.getById(id);
	}

	@Override
	public Discount getReferenceById(Long id) {
		return discountRepository.getReferenceById(id);
	}

	@Override
	public <S extends Discount> S save(S entity) {
		return discountRepository.save(entity);
	}

	@Override
	public <S extends Discount> List<S> saveAll(Iterable<S> entities) {
		return discountRepository.saveAll(entities);
	}

}
