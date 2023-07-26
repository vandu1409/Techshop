package com.vandu.service.Impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.hibernate.loader.ast.spi.Loadable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vandu.dto.VoucherApplicationResultDTO;
import com.vandu.dto.VoucherDto;
import com.vandu.enums.DiscountType;
import com.vandu.helper.UserHelper;
import com.vandu.model.User;
import com.vandu.model.Voucher;
import com.vandu.repository.VoucherRepository;
import com.vandu.service.CartService;
import com.vandu.service.VoucherService;

@Service
public class VoucherServiceImpl implements VoucherService {
	@Autowired
	private VoucherRepository voucherRepository;
	
	@Autowired
	private CartService cartService;

	@Override
	public <S extends Voucher> S save(S entity) {
		return voucherRepository.save(entity);
	}

	@Override
	public Page<Voucher> findByStatus( Pageable pageable, int status) {
		switch (status) {
		case 0:
			return findAll(pageable);
		case 1: // 1 là danh sách voucher còn hạn
			return findValidVouchers( pageable);
		case 2:
			return findExpiredVouchers( pageable);
		default:
			throw new IllegalArgumentException("Invalid status value: " + status);
		}

	}
	

	@Override
	public VoucherApplicationResultDTO applyVoucher(Long voucherId,Long uid) {
		
		Voucher voucher = voucherRepository.findById(voucherId).orElse(null);
		VoucherApplicationResultDTO voucherResult = new VoucherApplicationResultDTO();
		
		Double discountAmount = 0.0;
		
		double totalPrice = cartService.getAllCart(uid).stream()
				.mapToDouble(cart -> {
					return cart.getSelectedItems()? cart.getProductDetails().getDiscountedPrice() * cart.getQuantity() : 0;
				}).sum();
		
		if(voucher==null) {
			voucherResult.setDiscountAmount(discountAmount);
			voucherResult.setDiscountedPrice(totalPrice);

			return voucherResult;
		}

		

		
		System.err.println(totalPrice);

		LocalDate today = LocalDate.now();

		if (!today.isBefore(voucher.getStartDate()) && !today.isAfter(voucher.getEndDate())) {
			if (totalPrice >= voucher.getAmountApplied()) {
				

				if (voucher.getDiscountType().equals(DiscountType.DISCOUNTBYPERCENT)) {
					discountAmount = totalPrice * (voucher.getDiscountValue() / 100);

				} else {
					discountAmount = voucher.getDiscountValue();
				}

				if (discountAmount > voucher.getMaxValue()) {
					discountAmount = voucher.getMaxValue();
				}

				totalPrice = totalPrice - discountAmount;
			}

		}
		
		voucherResult.setDiscountAmount(discountAmount);
		voucherResult.setDiscountedPrice(totalPrice);
		
		return voucherResult;
	}
	
	@Override
	public VoucherDto findVoucherDtoById(Long id) {
		Voucher voucher = findById(id).orElse(null);
		VoucherDto voucherDto = new VoucherDto();
		
		BeanUtils.copyProperties(voucher, voucherDto);
		
		voucherDto.setDiscountType(voucher.getDiscountType()==DiscountType.DISCOUNTBYPRICE ?1:2);
		
		return voucherDto;
		
	}

	@Override
	public Page<Voucher> findValidVouchers(Pageable pageable) {
		LocalDate today = LocalDate.now();
		return voucherRepository.findValidVouchers(today, pageable);
	}

	@Override
	public Page<Voucher> findExpiredVouchers(Pageable pageable) {
		LocalDate today = LocalDate.now();
		return voucherRepository.findExpiredVouchers(today, pageable);
	}

	@Override
	public List<Voucher> findValidVouchers() {
		LocalDate today = LocalDate.now();
		return voucherRepository.findValidVouchers(today);
	}

	@Override
	public <S extends Voucher> List<S> saveAll(Iterable<S> entities) {
		return voucherRepository.saveAll(entities);
	}

	@Override
	public <S extends Voucher> Optional<S> findOne(Example<S> example) {
		return voucherRepository.findOne(example);
	}

	@Override
	public List<Voucher> findAll(Sort sort) {
		return voucherRepository.findAll(sort);
	}

	@Override
	public void flush() {
		voucherRepository.flush();
	}

	@Override
	public Page<Voucher> findAll(Pageable pageable) {
		return voucherRepository.findAll(pageable);
	}

	@Override
	public <S extends Voucher> S saveAndFlush(S entity) {
		return voucherRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Voucher> List<S> saveAllAndFlush(Iterable<S> entities) {
		return voucherRepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Voucher> findAll() {
		return voucherRepository.findAll();
	}

	@Override
	public List<Voucher> findAllById(Iterable<Long> ids) {
		return voucherRepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Voucher> entities) {
		voucherRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Voucher> Page<S> findAll(Example<S> example, Pageable pageable) {
		return voucherRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Voucher> findById(Long id) {
		return voucherRepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Voucher> entities) {
		voucherRepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Long id) {
		return voucherRepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		voucherRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Voucher> long count(Example<S> example) {
		return voucherRepository.count(example);
	}

	@Override
	public <S extends Voucher> boolean exists(Example<S> example) {
		return voucherRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		voucherRepository.deleteAllInBatch();
	}

	@Override
	public Voucher getOne(Long id) {
		return voucherRepository.getOne(id);
	}

	@Override
	public <S extends Voucher, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return voucherRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return voucherRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		voucherRepository.deleteById(id);
	}

	@Override
	public Voucher getById(Long id) {
		return voucherRepository.getById(id);
	}

	@Override
	public void delete(Voucher entity) {
		voucherRepository.delete(entity);
	}

	@Override
	public Voucher getReferenceById(Long id) {
		return voucherRepository.getReferenceById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		voucherRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Voucher> entities) {
		voucherRepository.deleteAll(entities);
	}

	@Override
	public <S extends Voucher> List<S> findAll(Example<S> example) {
		return voucherRepository.findAll(example);
	}

	@Override
	public <S extends Voucher> List<S> findAll(Example<S> example, Sort sort) {
		return voucherRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		voucherRepository.deleteAll();
	}

}
